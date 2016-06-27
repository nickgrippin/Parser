# Parser
Just a simple CSV parser

## Running
This project requires SBT to run.

It takes one command line argument in the form of an absolute file path (eg `run /path/to/file`).  If no command line
argument is provided or the file is unable to be found, the program will run with a default clean test file and print 
a warning.

## Output
If the parser is able to run successfully, it will print out "SUCCESS". 

If there is an error in the transmittal sheet (on the first line), it will print out "TS ERROR", followed by the 
line data.

If there is an error in any of the rows of LAR data, the program will print out a message with the line number and field 
name.  The line number is 1-based (so the minimum line number would be 2 for LAR data).  The field name is the exact field
within the LAR that has failed to parse correctly.

If there is an incorrect number of fields, the program will print out a message with the line number and the number of
fields that were found.

## Test Files
There are two test files provided in this project.

'testClean.txt' will run successfully, and output a "SUCCESS" message.  This file will also be run as the default if
no argument is provided or if the file provided is not able to be found.

'testDirty.txt' will run if passed in as an argument ('run /path/to/project/src/main/resources/testDirty.txt').  It
will not run successfully, and should output a number of errors.

