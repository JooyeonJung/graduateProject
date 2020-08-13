#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
#path = '../.spyder-py3/codes/chunks2/chunk0.csv'
path = './chunk_re0.csv'
data = pd.read_csv(path, encoding = 'utf-8')
category_name = pd.read_csv('./category_name.csv', encoding = 'utf-8')
data.head(5)
data = data[['name_token', 'cate_one', 'keyword']]


# In[2]: 카테고리 이름 매핑


category_name = pd.read_csv('./category_name.csv', encoding = 'utf-8')
new_cate = pd.DataFrame({'category' : category_name['category'],
                       'cate_name': category_name['cate_name']})
cate = new_cate.set_index('category').to_dict()
cate2 = dict(zip(new_cate.category,new_cate.cate_name))
cate2


data['cate_name'] = data['cate_one'].apply(lambda x:cate2[x])
data['name_and_keyword'] = data['name_token'] + " " + data['keyword']
data


# In[3]: 0~17로 카테고리 번호 새로 생성


data = data[['name_and_keyword','cate_name']]
data
data['category_id'] = data['cate_name'].factorize()[0]
category_id_df=data[['cate_name', 'category_id']].drop_duplicates().sort_values('category_id')
category_to_id = dict(category_id_df.values)
id_to_category = dict(category_id_df[['category_id', 'cate_name']].values)
data



# In[6]: 카테고리별 개수


import matplotlib.pyplot as plt
fig = plt.figure(figsize=(8,6))
data.groupby('category_id').name_and_keyword.count().plot.bar(ylim=0)
plt.show()


# In[7]: 벡터화


from sklearn.feature_extraction.text import TfidfVectorizer

tfidf = TfidfVectorizer()
features = tfidf.fit_transform(data.name_and_keyword.values.astype('U')).toarray()
labels = data.category_id
features.shape


# In[ ]: 상위 키워드 뽑기


from sklearn.feature_selection import chi2
import numpy as np

N = 20
for cate_name, category_id in sorted(category_to_id.items()):
  features_chi2 = chi2(features, labels == category_id)
  indices = np.argsort(features_chi2[0])
  feature_names = np.array(tfidf.get_feature_names())[indices]
  unigrams = [v for v in feature_names if len(v.split(' ')) == 1]
  print("# '{}':".format(cate_name))
  print("  . Most correlated unigrams:\n       . {}".format('\n       . '.join(unigrams[-N:])))




