# Clothing-App

Clothing app based on various seasons

**Introduction**

Clothing App is a database of clothing items where a user can select, create, update and remove the clothing items based
on his/her choice.

**ERD**

https://lucid.app/lucidchart/8fcb9860-b388-4bec-87c3-3af000f08b82/edit?page=0_0&invitationId=inv_29714ec0-cf07-4804-9b12-d2d4ad5ad373#

![Screen Shot 2022-01-03 at 12 59 31 PM](https://user-images.githubusercontent.com/94148009/147969116-32cea096-dd6a-4e67-90e3-b241a4ec102c.png)

**User Stories**

**Item Model**

1. As a User, I should be able to create an item based on a category, season and gender.
2. As a User, I should be able to update an item.
3. As a User, I should be able delete an item.
4. As a User, I should be able to get an item or items based on a category.

**Category Model**

1. As a User, I should be able to create a category.
2. As a User, I should be able to update a category.
3. As a User, I should be able to get all categories.
4. As a User, I should be able to delete a category and all items within it.

**Seasons Model**

1. As a User, I should be able to create a season.
2. As a User, I should be able to delete a season and all the items within it.
3. As a User, I should be able to get a season all the items within it.

**Gender Model**

1. As a User, I should be able to create male or female gender.
2. As a User, I should be able to get all items based on the gender.



**END POINTS**

Method/Endpoints/Description
1. GET	 api/items returns JSON all items

2. POST	api/items	creates new item

3. GET	api/items/{itemID}	returns JSON for an item

4. PUT	api/books/{itemID}	updates specific item

5. DELETE	api/items/{itemID}	deletes specific item

6. GET	api/categories	returns JSON of all categories

7. POST	api/categories	creates new category
******
