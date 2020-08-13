import sys
import os
import pandas as pd
import re
from konlpy.tag import Okt
from collections import Counter

# input = './data/chunks_more/chunk1.csv'
# output = './data/chunks_more/chunk_result1.csv'

def removeNullRows(filename1, filename2):
    '''
    1. null 있는 행 모두 제거 dropna()
    2. description열에서 '상세 설명 없음'도 null로 취급, 모두 제거
    3. description열에서 중복있으면 처음 행만 남기고 제거
    4. name, description 열 동시에 중복 있으면 처음 행만 남기고 제거
    '''
    db = pd.read_csv(filename1, encoding='utf-8')

    col = ['name','keyword', 'category_id', 'description']
    db = db[col]
    dbframe = pd.DataFrame(db)

    # remove rows have null value
    dbframe = dbframe.dropna(axis=0)
    # remove no description rows
    dbframe = dbframe[dbframe.description != '상세 설명 없음']
    # remove duplicated rows (only 'description' column duplicated)
    dbframe = dbframe.drop_duplicates(['description'])
    # remove duplicated rows ('name', 'description' both columns duplicated)
    dbframe = dbframe.drop_duplicates(['name', 'description'])
    
    dbframe.to_csv(filename2,encoding='utf-8', index='False')
    
def preprocessOkt(filename1, filename2):
    '''
    1. https:// url 주소들 먼저 제거(openkakao, youtube, etc)
    2. 이모티콘, 이모지 등 특수문자 모두 제거 후 기본 한/영/숫자 만 남김
    3. stopwords는 토큰에서 상위100개를 선정(stopwords.txt에 별도 저장) 후 해당 단어들 제거
        - //stopwords 추가 여부???
    4. 토큰화 후 null가진 행 모두 제거
    '''

    db = pd.read_csv(filename1, encoding='utf-8', index_col=0)
    okt = Okt()


    def okt_token(text):
        # leave noun, alpha(alphabet), number
        s = ''
        pos = okt.pos(text, norm=True, stem=True)
        for keyword, type in pos:
            if type == 'Noun' or type == 'Alpha' or type == 'Number':
                s = s + ' ' + keyword
        return s


    def cleanData(readData):
        '''remove all the special patterns in data
           ^url pattern -> ^special chars -> kor/eng/num chars
        ''' 

        include_pattern = re.compile(r'[^가-힣a-zA-Z0-9.]')  # include basic kor/eng/num chars
        exclude_pattern = re.compile("["
                                     u"\U0001F600-\U0001F64F"  # emoticons(emoji)
                                     u"\U0001F300-\U0001F5FF"  # symbols & pictographs(emoji)
                                     u"\U0001F680-\U0001F6FF"  # transport & map symbols(emoji)
                                     u"\U0001F1E0-\U0001F1FF"  # flags (iOS)(emoji)
                                     r"-=+,#/\?:^$.@*\"※~&%ㆍ!』\\‘|\(\)\[\]\<\>`\'…\\n》'"  # special chars
                                     r"\d\d+원"
                                     r"\d\dml"
                                     r"\d\dcm"  # price volume length info
                                     "]+", flags=re.UNICODE | re.IGNORECASE)
        url_pattern = re.compile(r'[a-z]+://[a-z0-9.-_]+', flags=re.IGNORECASE)
        cleaned_data = readData.str.replace(pat=url_pattern, repl=r'', regex=True)
        cleaned_data = cleaned_data.str.replace(pat=exclude_pattern, repl=r' ', regex=True)
        cleaned_data = cleaned_data.str.replace(pat=include_pattern, repl=r' ', regex=True)
        return cleaned_data


    '''category id preprocessing--------------------------'''
    db['category_id'] = db['category_id'].astype('int')

    # make all category_ids(3,6,9digits) as 9digits uniformly
    def category_id_9(num):
        while num < 1000000:
            num *= 1000
        return num


    # apply 9dits function
    db['category_id'] = db['category_id'].map(category_id_9)

    # Separated into category_id by one(대), two(중), three(소)
    db['cate_one'] = db['category_id'].map(lambda x: int(x / 1000000))  # 대
    db['cate_two'] = db['category_id'].map(lambda x: int((x % 1000000) / 1000))  # 중
    db['cate_three'] = db['category_id'].map(lambda x: int(x % 1000))  # 소

    #del db['category_id']
    print('category id')
    

    '''name and description preprocessing--------------------------'''
    # replace data with applying cleanData function
    db['name'] = cleanData(db['name'])
    db['description'] = cleanData(db['description'])
    print('clean data')

    # token noun, alpha, number(apply okt_token function)
    db['name_token'] = db['name'].map(okt_token)
    db['description_token'] = db['description'].map(okt_token)
    print('okt token')

    tokens = list()


    def sp_f(text):
        for wo in text.split(' '):
            tokens.append(wo)

    # find stop words in description_token column and make them string
    db['description_token'].map(sp_f)
    count = Counter(tokens)
    most_common_list = []
    for k, v in count.most_common(100):
        most_common_list.append(k)
    stopword_str = ' '.join(map(str, most_common_list))

    # //we need to add more stopwords manually
    more_stopwords = ''
    stopword_str = stopword_str + ' ' + more_stopwords
    
    #save 100 most common stopword(str) in stopwords.txt
    with open('./stopwords.txt', 'w')as f:
        f.write(stopword_str)
    print('stopword.txt saved')
    
    stop_word = stopword_str.split(' ')


    def remove_stopword(word):
        for w in stop_word:
            word = word.replace(w, '')
        return word

    # apply remove_stopword function 
    db['name_token'] = db['name_token'].map(remove_stopword)
    db['description_token'] = db['description_token'].map(remove_stopword)

    dbframe = pd.DataFrame(db)

    # remove rows having missing values after preprocessing
    dbframe = dbframe.dropna(axis=0)
    # finally save to csv file
    dbframe.to_csv(filename2, encoding='utf-8', index='False')


if __name__ == '__main__':
    removeNullRows('./data/product_4categories_more.csv', './data/nonull.csv')
    preprocessOkt('./data/nonull.csv', './data/test.csv')
