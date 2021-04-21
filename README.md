# MongoDB Coding Challenge

## Purpose of Challenge
The aim of the MongoDB Coding Challenge is to write a program that takes a JSON object as an input and returns a flattened version of the JSON object as an output. My solution to this challenge is implemented in Java.

## Assumptions
- JSON objects will be stored in files and tested by entering the file name into the command line terminal so that the program can read the JSON file.
- JSON objects will span across several lines rather than written in one line
 
---

## How to Run the Program
-	Store any JSON files to be tested as input in the same folder as the solution file
-	Compile the solution file via the command line by typing the following:
```
javac JSONSolution.java -d bin
```
-	Run the solution program with the JSON file via the command line by typing the following:
```
java -cp bin JSONSolution.java YOURFILE.json
```
- An output file called ‘YOURFILE-Flattened.json’ should appear in the folder where the solution file is. This output file should contain the flattened version of the inputted JSON object.

## How the Program Works
-	The program checks if a JSON input file was typed into the command line. If there is no file, an appropriate error message will appear in the terminal.
-	If a valid JSON file is inputted, the program will create an output file to write out the flattened JSON object.
-	The program will look at the JSON object line by line to check for embedded documents and it will write each line onto the output file.
-	If an embedded document is found, the program will store the field and the embedded document using substrings.
-	The program will then write the field and the embedded document onto the output file as its flattened version.

---

## Tests
I conducted a couple of tests to ensure the solution works with different JSON objects

### - Provided example:
```JSON
{
    "a": 1,
    "b": true,
    "c": {
        "d": 3,
        "e": "test"
    }
}
```

Output:
```JSON
{
    "a": 1,
    "b": true,
    "c.d": 3,
    "c.e": "test"
}
```

### - Multiple embedded documents:
```JSON
{
    "author": {
        "name": "Sinead",
        "age": 19
    },
    "country": "Ireland",
    "Apple": {
        "Product": "iPhone",
        "colour": "Purple"
    }
}
```

Output:
```JSON
{
    "author.name": "Sinead",
    "author.age": 19,
    "country": "Ireland",
    "Apple.Product": "iPhone",
    "Apple.colour": "Purple"
}
```

### - No embedded documents:
```JSON
{
    "char": "a",
    "string": "abc",
    "integer": 1,
    "bool": true
}	
```

Output:
```JSON
{
    "char": "a",
    "string": "abc",
    "integer": "1",
    "bool": true
}
```

### - Empty JSON object:
```JSON
{}
```

Output:
```JSON
{}
```

---

## Time
I spent 2 hours and 28 minutes in total on this coding challenge.
