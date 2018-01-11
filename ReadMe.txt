I use POSTMAN to access the API and i recommend that you import the file Avenue.postman_collection.json into it.

http://localhost:8080/h2-console
jdbc URL : jdbc:h2:mem:testdb

1) Create, update and delete products
Change to POST. On the tab Body, set the body as 'raw' and select JSON (application/json).

POST localhost:8080/avenue/products
{
"name" : "Console",
"description" : "Console players",
"parentProducts" : [
    {
    "name" : "PS2",
    "description" : "PS2 console"
    },
    {
    "name" : "PS3",
    "description" : "PS3 console"
    },
    {
    "name" : "PS4",
    "description" : "PS4 console"
    },
    {
    "name" : "Xbox 360",
    "description" : "Xbox 360 console"
    },
    {
    "name" : "Xbox one",
    "description" : "Xbox one console"
    }
    ]

}

PUT localhost:8080/avenue/products/{id}
{
    "id" : 1
    "name" : "Consoles",
    "description" : "Console players",
}

DELETE localhost:8080/avenue/products/{id}

2) Create, update and delete images
POST localhost:8080/avenue/images
    * set the option to form-data. set key as 'file' and value as file, then select the image.

PUT localhost:8080/avenue/images/{id}

DELETE localhost:8080/avenue/images/{id}

3) Get all products excluding relationships (child products, images) 
GET localhost:8080/avenue/products/only

4) Get all products including specified relationships (child product and/or images) 
GET localhost:8080/avenue/products

5) Same as 3 using specific product identity 
GET localhost:8080/avenue/products/only/{id}

6) Same as 4 using specific product identity 
GET localhost:8080/avenue/products/{id}

7) Get set of child products for specific product 
GET localhost:8080/avenue/products/childs/{id}

8) Get set of images for specific product
GET localhost:8080/avenue/products/images/{id}