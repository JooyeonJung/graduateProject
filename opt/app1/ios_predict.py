#!/usr/bin/env python3

import pickle
import pandas as pd
import numpy as np
import re
from sklearn.externals import joblib
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.preprocessing import LabelEncoder

def predict_category(option):
	model = joblib.load('/opt/app1/download/svm2_k.pkl')
	en = LabelEncoder()
	en.classes_ = np.load('/opt/app1/download/classes_k.npy', allow_pickle=True)
	keywords = option


	count_vect = pickle.load(open('/opt/app1/download/cv.pkl', 'rb'))
	tfidf_transformer = pickle.load(open('/opt/app1/download/tfidf.pkl', 'rb'))

	keywords = pd.Series([keywords])
	keywords_count = count_vect.transform(keywords)
	keywords_tfidf = tfidf_transformer.transform(keywords_count)

	temp = model.predict_proba(keywords_tfidf)
	predict = top_n(temp, 2)
	predict = top_n_label(predict, en)
#	print('The category is estimated to be : ' + str(predict.iloc[0][0]) +'\n')
#	return str(predict.iloc[0])
	return predict.to_json(orient='records', force_ascii=False)

def top_n_list(data, n):
	return list(map(lambda x: np.argpartition(x, -n)[-n:][0],data))


def top_n(result, n):
    N = n
    predict = pd.DataFrame([top_n_list(result, i) for i in np.arange(1, N+1)]).T
    predict.columns = ["Top" + i for i in list(np.arange(1,N+1).astype(str))]
    return predict

def top_n_label(predict, encoder):
#    predict['label'] = label.tolist()
    for i in predict.columns.tolist():
        if re.search("Top", i) is not None:
            predict[i] = encoder.inverse_transform(predict[i])
        else:
            pass
    return predict

