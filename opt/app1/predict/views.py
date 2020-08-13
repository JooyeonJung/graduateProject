from django.shortcuts import render

from django.http import HttpResponse, JsonResponse
from rest_framework.views import APIView
from ios_predict import predict_category

# Create your views here.


def testfun(request, word):
	s = "Keyword : " + word
	return HttpResponse(s)

def display(request, word):
#	s = "Keyword : " + word + "\n" + "Prediction : " + predict_category(word)
	return HttpResponse(predict_category(word), content_type=u"application/json;charset=utf-8")
#	return JsonResponse(predict_category(word), safe=False)
