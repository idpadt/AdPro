# ADVANCED PROGRAMMING

## TUTORIAL 1

### Reflection 1

#### Clean Code Principles
- Meaningful names (yes) = Not the hardest thing to do. Just remember a long but meaningful name is better than a short but ambiguous or useless name.
- Function (yes) = I think I've made small things that do one thing. The "Command Query Separation" definitely helped in determining if a function is clean or not.
- Comments (maybe) = I think my code is clean enough to not need any comments explaining additional info. the code is pretty self-explanatory.
- Objects and data structures (no) = I have added some custom exceptions, and have used throws and try-catch. I have also made sure that functions doesn't return nulls. But I don't think I have made a "fool-proof" code that is complete with throws and try-catch for every possible scenario.

#### Secure Coding
- Authentication (no)
- Authorization (no)
- Output Data Encoding (no)
  
Things like authorization, authentication, and output data encoding are very hard things to do, even so for a small timeframe.

- Input Data Validation (some) = I did do input validation, although there are still some bugs (e.g. submitting string in a number form will not accept it, but the error message show to user is not customized).

### Reflection 2

After writing the unit test, how do you feel?   

->Tired. Very tired. And much more complicated than just simply making the website itself, because I'm thinking of what are the test cases I need to add.

How many unit tests should be made in a class?   

-> Enough for cover most, if not all, code. And also for every possible scenario, i.e. positive and negative scenarios.

How to make sure that our unit tests are enough to verify our program?  

-> If every possible scenario is covered, and have near 100% code coverage is when it is enough. However, testing is an ongoing part of a software development lifecycle. As we make new features, or new changes, new tests are always needed.

It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?  

-> Not exactly. As I have said earlier, every possible scenario also needs to be considered. For example an integer input form, which is one part of the code, has multiple scenarios which needs multiple tests, e.g. testing for out-of-bounds, near out-of-bounds, in-bound inputs.

Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality?  Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

-> Of course that scenario would decrease the cleanliness of the code, and in turn reduce code quality. The first thing in mind is duplication, i.e. a lot of the code will be repeated. And this will introduce more problems, such as reduced readability, increased code size, individualized test suites (which makes it harder to improve), etc.

-> A simple fix would be to make a shared base class so that code aren't repeated.

## TUTORIAL 2

### Reflection

#### 1.List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

One of the code quality issues listed by SonarCloud was simply "remove public modifier" in module-2-exercise branch, where i first added SonarCloud.
It points to almost all unit test classes (ie ProductServiceTest, ProductControllerTest, etc.) was public, and SonarCloud's advice was simply deleting the word "public".
The result was the classes uses the default modifier, ie protected.

#### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?

For CI, yes. My current GitHub actions includes thing like OSSF Security Scorecard (for security), custom CI (for testing), and SonarCloud (also for testing).
By definition, I've met the minimum requirements for CI.

However for CD, not yet. I am currently struggling with Koyeb and still cannot deploy.
I also don't have any yml or GitHub actions related to CD.