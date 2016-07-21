# Parser
Just a simple CSV parser

## Running
This project requires SBT to run.

After running (simply using `run`), a file chooser will open up.  Navigate to and select the file you want to test.  If
the file is not found by the program, it will exit.

Note that there is a bug where the file chooser might not pop up the first time the project is run.  Simply run the project
again to see the file chooser appear.

## Output
If the parser is able to run successfully, it will print out "SUCCESS". 

If there is an error in the transmittal sheet (on the first line), it will print out "TS Errors", followed by the 
field that threw the error.

If there is an incorrect number of fields in a LAR row, the program will print out a message with the line number and the 
number of fields that were found.  No other parsing checks will be done for that row.

If there is an error parsing a field in a LAR row, the program will print out a message with the line number and field 
name.  Line numbers start at 2 (the first row is the transmittal sheet information).  The field name is the exact field
within the LAR that has failed to parse correctly.

The following is example output from the 'testDirty.txt' file:
```
TS Errors:
	Record Identifier is not an Int
	Timestamp is not a Long
Errors on LAR #14
	Record Identifier is not an Integer
Errors on LAR #17
	Record Identifier is not an Integer
	Agency Code is not an Integer

```

## Test Files
There are two test files provided in this project.

'testClean.txt' will run successfully, and output a "SUCCESS" message.  This file will also be run as the default if
no argument is provided or if the file provided is not able to be found.

'testDirty.txt' will run if selected through the file chooser.  It should not run successfully, and should output a 
number of errors.

