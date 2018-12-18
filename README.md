## EDITOR APP
https://github.com/shubdhingra/editor/
### Author - Shubham Dhingra

#### Description:
This application is majorly developed for different publishing houses, where various articles can be present from various authors.
With this application, u user can not only read an article from a publishing database, but also can create, modify and delete the article entry into the database.
It is very simple and generic to use. Moroever, this application enables a user to read/fetch the article by filtering on the basis of authors, keywords, etc.

#### Pre-Requisites:
Before executing Editor App , you may require:
Java
SpringBoot
Gradle
TestNg
any IDEs, etc.

#### Steps To Setup:
1. Import the files into an IDE.
2. Once done, right click the root folder of the project and select gradle refresh/gradle build. This will download all the necessary libraries, the project requires.
3. Once downloaded, now you can again right click the project root folder and select Run As SpringBoot Application , under Run as option.
4. Once execute successfully, click on the link http://localhost:8500/swagger-ui.html to have the feel of the application.
5. Utilise various APIs listed, as per the needs.

#### Features:
Editor application consists of various APIs as different features mentioned below :
1.  A user can a create an article by providing the required details of the same
2. A user can update an already existing article by providing the artile id as input and the payload as body.
3.A user can delete an article on the basis of artilce id from editor application.
4. A user can read each article using the artilce id.
5.  A user can fetch all the artilces available in the editor database.
6. A user can fetch articles from some particular author in the editor database.
7. A user can fetch an article which consist of some particular keyword as a filter.
8. A combination of above 2 filters can also be applied, while fetching the articles.

#### Future Scope:
Due to the time constraint, this application only caters the most basic needs of a publishing house.
But the scope of this application is much broader and needs to be expanded further by implementing below features as future plan :
1.  Another filter based on period/publishing date needs be added while fetching the articles.
2. Security aspects needs to be added by allowing rescticted access or scope based access only using JWT tokens. 
3. As per now, basic required test cases have been implemented, which can be extended to cover entire code base.

#### Project Documentation:
1. Swagger Documentation - This document will provide a detailed information about the various APIs being provided as various features in       this application.
Path : editor/editor/api/Editor.yml
2. Java Docs - editor/editor/doc/
3. Test reports - This document briefly describes the nature and amount of test cases written for this project.
Path : editor/editor/test-output/Default suite/Default test.html

#### Article properties :
An article comprises of below mentioned properties :
1. Header
2. Description
3. Text
4. Keywords
5. Author
6. PublishDate

An article is considered as duplicate if it has same values for author and Header.

#### License:
This is an open source project and you are free to copy, modify, and distribute the project.
