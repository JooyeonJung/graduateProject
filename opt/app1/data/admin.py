from django.contrib import admin

from .models import Category, Product, Temp
from import_export.admin import ImportExportModelAdmin

# Register your models here.

#admin.site.register(Category)
#admin.site.register(Product)
#admin.site.register(Temp)


@admin.register(Category, Product, Temp)
class ViewAdmin(ImportExportModelAdmin):
	pass
