Customer review testing code for Home Depot

Question 1) related files: ReviewAnalysisManager, ReviewAnalysisService, ReviewAnalysisServiceImpl

I have two implementations for this question. 

The first one is in file ReviewAnalysisManager. It makes use of the existing CustomerReviewManager to get all reviews for a given product. Then it use either parallelStream in Java 1.8 or loop thurs the list of CustomerReview in Java 1.7 to calculate the total of the required range.

The second implementation provides at service level which is the same as existing CustomerReviewService. It includes two files: ReviewAnalysisService and ReviewAnalysisServiceImpl. It uses CustomerReviewSerivce to get all the reviews for a given product at data model layer. Then it uses either parallelStream in Java 1.8 or loop thur the list of CustomerReview in Java 1.7 to calculate the total of the required range.

Question 2) related files: CurseWord, CurseWordFromFile, CommentValidator, RatingValidator, CustomerReviewController

a) related files: interface CurseWord, class CurseWordFromFile
CurseWordFromFile implements interface CurseWord which reads the list of curse words from a file. I create interface CurseWord to make it flexible to add another implementation such as read from database in future.

b) CommentValidator implements Spring validator interface. I use Spring validator interface so that each validator has independent logic. It injects CurseWordFromFile bean to provide the list of curse words to check against any given comment.

c) RatingValidator also implements Spring validator interface. It has independent logic for the rating only.

Since this isn't any controller in the provided source code. I add a CustomerReviewController to show how the above two validators work before creating the customer review if all the rules are passed.

Question 3) related files: customerreview-spring.xml

I add the following bean id in the context file to make the new functionality can be used elsewhere in the application.

id="curseWordList" 
 
id="commentValidator"
 
id="ratingValidator"

id="reviewAnalysisService"
