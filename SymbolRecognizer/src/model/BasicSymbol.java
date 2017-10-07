package model;

/**
 * An interface made to logically divide the types of Symbols. Although shared methods are not used
 * between the classes that currently implement this, they do share the unique quality that
 * they are the only types of symbols that can be added to the model.SymbolsBank. This also allows
 * prepares our code for future expansion of additional basic symbols.
 */
public interface BasicSymbol extends Symbol {

}
