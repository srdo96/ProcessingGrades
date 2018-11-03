# ProcessingGrades

For this assignment you need to read a .csv file and prepare grades with some statistics. The base code and demo data (used in class) is linked with this assignment.

Tasks:
1) Read the csv file
2) The total marks for each assessment criteria is in row 2 of that file. Do not assume that they will stay the same in the data file I test your program with. The number of quizzes, midterm, final, absent -- all these columns will stay the same, though.
3) Tokenize each line to get the id, name, absent count, quiz scores, midterm marks and term final marks
4) Calculate the attendance marks: a student will not lose marks for missing 0 or 1 classes. For any extra class that student misses, he/she will lose 0.5 marks.
5) The best 4 quizzes out of 5 will get counted.
6) The total score is the summation of attendance, assessment, midterm and final.
7) Based on the total score, we will calculate the letter grade. You need to follow SEU grading scheme to convert numeric score to the letter grades (80+ == A+, 75-79 == A, and so on).
8) For each assessment criteria calculate the minimum, maximum, average and standard deviation
9) Calculate a histogram of grades given. You need to print the frequency of A+s, As, etc.
10) All the output for the program should be printed on the console.
