package model;

import java.util.List;

/**
 * Represents the SymbolsBank interface. The SymbolsBank interface is a collection of methods used
 * to add, store, and report back any stored Symbols.
 */
public interface SymbolsBank {

  /**
   * Adds a basic symbol (line or circle) to the bank of symbols. If this added symbol would allow
   * for a complex symbol to be made (i.e. a snowman or a triangle, construct such, and remove
   * the (basic) components that made the complex symbol.
   */
  public void addSymbol(Symbol aSymbol);

  /**
   * Reports back what symbols are stored in the bank.
   * @return a clone of the symbols stored in the Symbols Bank.
   */
  public List<Symbol> symbolsReport();

  /**
   * Reports back a copy of the basicSymbols contained in the model.SymbolsBank.
   * @return a list of symbols containing the basicSymbols.
   */
  public List<Symbol> basicSymbolsReport();

  /**
   * Reports back a copy of the compositeSymbols contained in the model.SymbolsBank.
   * @return a list of symbols containing the compositeSymbols.
   */
  public List<Symbol> compositeSymbolsReport();

}
