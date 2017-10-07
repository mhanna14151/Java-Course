README FOR ASSIGNMENT 9

###1.0 DESIGN###
#1.1 INTERFACES
There are four interfaces: SymbolBank, model.Symbol, model.BasicSymbol, and model.CompositeSymbol.
Although without explicit methods, we have decided to organize in this manner to align similar-type
symbols in the future. The model.SymbolsBank contains the main methods that the controller will interface with.

#1.2 ADDING TO THE MODEL
The addSymbol method contains the bulk of organizing data within this model. It organizes the
symbols into their respective lists, as well as triggering helper methods to determine
and compile composite symbols.

#1.3  REPORTING
The three reporting methods, symbolsReport, basicSymbolsReport, and compositeSymbolsReport allows
the controller quicker ways of obtaining the data it needs in a relatively organized fashion.
symbolsReport for obtaining all symbols, basicSymbolsReport for only the basics, and
compositeSymbolsReport for only composites.

#1.4 MODIFIERS
Many of the methods are labeled with the protected access modifier to allow for future expansion
of classes to extend the classes already created without needing to rewrite them (more details
in the section 4.0 below. The symbols modifiers are also protected as they can be used
in other ways we are currently using.

#1.5 LOGIC FOR COMPILING COMPOSITES
Depending on what basic symbol was added, the logic lived within the model.SymbolsBankImpl class checks
and uses methods available to attempt to make composite symbols.  Each composite symbol has their
own static method to determine if you can make one.  This allows for you to check if you can
make a triangle, without actually having to instantiate one (paradoxical from our perspective of
making a triangle to run a method if you can make a triangle.  More details in section 3.3.
Composites can be made in the future using multiple basic symbols without needing to pass lists
multiple times between classes.

###2.0 IMPRECISION###
#2.1.1 THRESHOLDS
The thresholds reside in the model.Circle and model.Line classes.  Although ultimately used to help determine
if three lines can make a triangle, or three circles can make a snowPerson, the methods that
contain thresholds can also be used for making other composite symbols in the future.  They are
not necessarily tied to making a triangle or snowPerson.

Circles don't necessarily need to be in an exact line or exactly touching.
Lines don't need to intersect and a specific double point.

#2.1.2 THRESHOLD METHOD LIST
The following methods contain the thresholds mentioned in 2.1.1
model.Circle class:
-circlesInLine()
-circlesTouching()

model.Line class:
-intersectAtEnd()
-intersectAtStart()

###3.0 CONSIDERATIONS##
#3.1 LISTS
We have considered storing all symbols in a hashmap, distributed into separate classes containing
their own list of their respective shape, as well as into one list.  We have organized them
into one class as separate lists. We've done this so it is easier to move items between lists
(i.e. from basic symbol lists to composite symbol lists), as well as variations of reporting
in different combinations in the future. When determining composite symbols, you can look
only in the lists of shapes you need, rather than wasting time and space iterating through
symbols you know cannot make that composite.  For example, when looking to make a triangle, you
need not to look at the circle and snowPerson lists, but rather only the line list since you can
only make triangles from lines.

#3.2 GENERALIZATION AND GENERICS
We have considered using generic classes for basic and/or composite shapes, but the methods offered
are too different from one another in our thought process.

#3.3 DYNAMIC DISPATCH AND LOGIC FOR DETERMINING COMPOSITES
We have decided to have the model.SymbolsBankImpl contain the triggering of using methods to determine and
ultimately try to make a composite symbol. We also considered putting the logic within the
basic symbol class, e.g. whether you can make a triangle in the line class, but then the line class
would not represent just a line over time.  You would also need to change multiple classes with
the same code (e.g. if you want a snowPerson with two line arms, you need to add that check for
making a snowPerson in both the model.Circle and model.Line classes.  In our organization you will only need
to change the model.SymbolsBankImpl class, minimizing the methods you need to override.

###4.0 EXTENSION EXAMPLES###
With our design you can follow the example for extending this model to accommodate for adding
a new basic and composite symbol:

#4.1 ADDING A NEW BASIC SYMBOL
4.1.1 Make a new class, X, with your basic symbol that implements the basicSymbol interface.
4.1.2 Make a new class, Y, that extends the model.SymbolsBankImpl class
4.1.3 Y should contain a List<model.Symbol> with that your new symbol, X, will go into.
4.1.4 Override addSymbol and add the logic check for the instance of X.
4.1.5 Override the appropriate reporting methods.

#4.2 ADDING A NEW COMPOSITE SYMBOL
4.2.1 Make a new class, Z, with your composite symbol that implements the compositeSymbol interface.
4.2.2 Contain a static boolean method to determine if you can make Z or not with the basic symbols
needed.
4.2.3 Same steps as 4.2.1 and 4.2.2
4.2.4 Override the appropriate methods (e.g. if composite is a square,
override tryToMakeLineComposites to include the square.)
4.2.5 Add protected method that includes the logic to make a square (using the method in 4.2.2).