import pandas as pd

path = "./train_data.csv"
data = pd.read_csv(path, encoding = "utf-8", index_col = 0)
category = pd.read_csv('./bunjang_category.csv')

data2 = data[data['cate_two_2']>10000]
data2
#라벨 인코더 만들기
from sklearn.preprocessing import LabelEncoder
encoder = LabelEncoder()
encoder.fit(data2['cate_two_2'])
label1 = encoder.transform(data2['cate_two_2'])
train1 = data2['keyword_and_name'].astype(str)
#모델
from sklearn.feature_extraction.text import CountVectorizer #카운트 벡터
from sklearn.feature_extraction.text import TfidfTransformer  #TFIDF
from sklearn.model_selection import train_test_split  #데이터 나누기
from sklearn.svm import LinearSVC
from sklearn.naive_bayes import MultinomialNB # 다항분포 나이브 베이즈 모델
from sklearn.metrics import accuracy_score #정확도 계산
from sklearn.calibration import CalibratedClassifierCV

X_train, X_test, y_train, y_test = train_test_split(train1, 
                                                    label1, random_state = 0)#데이터 나누기
count_vect = CountVectorizer() #카운트 벡터
tfidf_transformer = TfidfTransformer() #TF-IDF

#training data에서는 fit_transform사용
X_train_counts = count_vect.fit_transform(X_train.values.astype('U'))
X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)

#test data에서는 transform사용
X_test_counts = count_vect.transform(X_test.values.astype('U'))
X_test_tfidf = tfidf_transformer.transform(X_test_counts)

#모델 생성 및 학습
svc= LinearSVC()
model1 = CalibratedClassifierCV(svc)
model1.fit(X_train_tfidf, y_train)

y_test_pred1 = model1.predict(X_test_tfidf)

print("TF-IDF 학습 후 tf-idf + svm : ", accuracy_score(y_test, y_test_pred1))
#모델, vectorizer, 라벨 인코더 저
import pickle
from sklearn.externals import joblib

joblib.dump(model1, './models/svm2.pkl')
with open('cv.pkl', 'wb') as f:
    pickle.dump(count_vect, f)
with open('tfidf.pkl', 'wb') as f:
    pickle.dump(tfidf_transformer, f)
    
import numpy as np

np.save('classes.npy', encoder.classes_)