#pandas로 데이터 불러오기
import pandas as pd


data = pd.read_csv('chunk0.csv', encoding='utf-8')
category = pd.read_csv('bunjang_category.csv', encoding='utf-8')
#data.head()

#column정리 후 keyword, category_id에서 null값 지우기
col = ['name', 'keyword', 'category_id', 'description']
data = data[col]
data = data[pd.notnull(data['keyword'])]
data = data[pd.notnull(data['category_id'])]
#data.to_csv("test.csv", encoding="utf-8")
data.shape

#import re
#re_sc = re.compile('[\!@#$%\^&\*\(\)\-=_\[\]\{\}\.,/\?~\+\'"|]')
#특수문자 제거

data['name'] = data['name'].str.replace(pat = r'[※★♥\!@#✔$%\^&\*\(\)\-=_\[\]\{\}\.,/\?~\+\'"|]', repl=r' ', regex=True) #특수문자제거
data['name'] = data['name'].str.replace(pat=r'[\s\s+]', repl= r' ', regex=True) #불필요한 공백
data['keyword'] = data['keyword'].str.replace(pat = r'[★♥\!@#$%\^&\*\(\)\-=_\[\]\{\}\.,/\?~\+\'"|]', repl=r' ', regex=True)
data['keyword'] = data['keyword'].str.replace(pat=r'[\s\s+]', repl= r' ', regex=True)
data['description'] = data['description'].str.replace(pat = r'[※✔💜★♥\!@#$%\^&\*\(\)\-=_\[\]\{\}\.,/\?~\+\'"|]', repl=r' ', regex=True)
data['description'] = data['description'].str.replace(pat=r'[\s\s+]', repl= r' ', regex=True)
data['description'] = data['description'].str.replace(pat=r'[\n\n+]', repl= r' ', regex=True)

#display(data)

print(data.isnull().sum()) #데이터 프레임 null값 확인

#test용 데이터프레임 생성
testpd = pd.DataFrame(data['keyword'], columns=['keyword'])
testpd['category_id'] = data['category_id']
#display(tfidfdata)

#category_id int형변환 및 대분류 추출
import numpy as np
#testpd = testpd[pd.notnull(testpd['category_id'])]
#testpd = testpd[pd.notnull(testpd['keyword'])]
print(testpd.isnull().sum())
def tokenizer_category_id(number):
    while (number > 1000) :
        number = number / 1000
    return int(number)

#int_id = testpd['category_id'].apply(tokenizer_category_id)
testpd['int_id'] = testpd['category_id'].apply(tokenizer_category_id)
testpd['int_id'] = testpd['int_id'].apply(np.int64)

display(testpd)

from sklearn.feature_extraction.text import CountVectorizer #카운트 벡터
from sklearn.feature_extraction.text import TfidfTransformer  #TFIDF
from sklearn.model_selection import train_test_split  #데이터 나누기
from sklearn.svm import LinearSVC
from sklearn.naive_bayes import MultinomialNB # 다항분포 나이브 베이즈 모델
from sklearn.metrics import accuracy_score #정확도 계산


'''name_token과 keyword함께 사용하는 data
testpd2 = pd.DataFrame(data['name_token'], columns=['name_token'])
testpd2['int_id'] = testpd['int_id']
testpd2['name_token2'] = testpd2['name_token'].apply(' '.join)
testpd2['keyword'] = data['keyword']
name_and_keyword = testpd2['name_token2'] + " " + testpd2['keyword']
X_train, X_test, y_train, y_test = train_test_split(name_and_keyword, tttttt['int_id'], random_state = 0)#데이터 나누
'''

#keyword만 사용
X_train, X_test, y_train, y_test = train_test_split(tfidfdata['keyword'], tfidfdata['int'], random_state = 0)#데이터 나누기
count_vect = CountVectorizer() #카운트 벡터
tfidf_transformer = TfidfTransformer() #TF-IDF

#training data에서는 fit_transform사용
X_train_counts = count_vect.fit_transform(X_train)
X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)

#test data에서는 transform사용
X_test_counts = count_vect.transform(X_test)
X_test_tfidf = tfidf_transformer.transform(X_test_counts)

#모델 생성 및 학습
#카운트벡터
model = MultinomialNB()
model.fit(X_train_counts, y_train)

model2 = LinearSVC()
model2.fit(X_train_counts, y_train)

#y_train_pred = model.predict(X_train_counts)
#y_train_pred2 = model2.predict(X_train_counts)
y_test_pred = model.predict(X_test_counts)
y_test_pred2 = model2.predict(X_test_counts)
y_test_pred_2 = model.predict(X_test_tfidf)
y_test_pred2_2 = model2.predict(X_test_tfidf)

#TF-IDF 벡터
model3 = MultinomialNB()
model3.fit(X_train_tfidf, y_train)

model4 = LinearSVC()
model4.fit(X_train_tfidf, y_train)

#y_train_pred3 = model3.predict(X_train_tfidf)
#y_train_pred4 = model4.predict(X_train_tfidf)
y_test_pred3 = model3.predict(X_test_tfidf)
y_test_pred4 = model4.predict(X_test_tfidf)
y_test_pred3_2 = model.predict(X_test_counts)
y_test_pred4_2 = model2.predict(X_test_counts)

print("count 학습 후 count + naive : ", accuracy_score(y_test, y_test_pred))
print("count 학습 후 count + svm : ", accuracy_score(y_test, y_test_pred2))
print("count 학습 후 tfidf + naive: ", accuracy_score(y_test, y_test_pred_2))
print("count 학습 후 tfidf + svm : ", accuracy_score(y_test, y_test_pred2_2))
print("TF-IDF 학습 후 tf-idf + naive : ", accuracy_score(y_test, y_test_pred3))
print("TF-IDF 학습 후 tf-idf + svm : ", accuracy_score(y_test, y_test_pred4))
print("TF-IDF 학습 후 count + naive: ", accuracy_score(y_test, y_test_pred3_2))
print("TF-IDF 학습 후 count + svm : ", accuracy_score(y_test, y_test_pred4_2))
