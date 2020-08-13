from django.db import models

# Create your models here.

class Category(models.Model):
	c_id = models.IntegerField(default=0)
	c_name = models.CharField(max_length=50)
	c_sname = models.CharField(max_length=20)
	c_pid = models.IntegerField(default=0)

	def __str__(self):
		return self.c_name

class Product(models.Model):
	p_id = models.IntegerField(default=0)
	p_uid = models.IntegerField(default=1000)
	p_name = models.CharField(max_length=100)
	p_price = models.IntegerField(default=0)
	p_keyword1 = models.CharField(max_length=30, blank=True)
	p_keyword2 = models.CharField(max_length=30, blank=True)
	p_keyword3 = models.CharField(max_length=30, blank=True)
	p_description = models.CharField(max_length=3000)
	p_cid = models.ForeignKey(Category, on_delete=models.SET_NULL, null=True)
	p_image = models.ImageField(blank=True, null=True)	
	
	def __str__(self):
		return self.p_name

class Temp(models.Model):
	t_id = models.IntegerField(default=0)
	t_name = models.CharField(max_length=30)

	def __str__(self):
		return self.t_name
	
