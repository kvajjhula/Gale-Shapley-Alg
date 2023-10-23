# Stable Matching Algorithm

## Description
This repository contains an implementation of the Gale-Shapley Stable Matching algorithm. This was developed as part of an Algorithms and Computational Complexity class at the University of Washington (CSE 417). My implementation is in Java

- Implements the Gale-Shapley Stable Matching Algorithm.
- The program also outputs the list of proposals made during the algorithm execution.
- Includes an input generator to create random preference lists.
- It also contains code to evaluate the 'goodness' of matches for both Men and Women.

## Results

- MGoodness and WGoodness were calculated for varying input sizes.

## Setup & Running

### Requirements
- Java JDK

### Steps
1. Clone the repository
2. Navigate to the folder containing the Java files
3. Compile the Java files:
   \`\`\`
   javac *.java
   \`\`\`
4. Run the program:
   \`\`\`
   java MainClass
   \`\`\`

## Files Included

- `Main.java` - The entry point for the application.
- `Man.java` - Class definition for a Man.
- `Woman.java` - Class definition for a Woman.

## Findings

The input generator was able to produce random permutations for testing the stable matching algorithm. Some observations based on varying input sizes are:

\`\`\`
Input size: 5
MGoodness: 1.6
WGoodness: 0.6

Input size 20
MGoodness: 2.85
WGoodness: 3.95

Input size 100
MGoodness: 5.84
WGoodness: 16.45

Input size 500
MGoodness: 5.768
WGoodness: 88.466

Input size 1000
MGoodness: 7.368
WGoodness: 144.005
\`\`\`

The WGoodness or W Rank drastically worsens as the input size grows, indicating that the women are generally worse off in the matches as the number of participants grows.
