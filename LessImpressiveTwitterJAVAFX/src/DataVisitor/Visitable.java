package DataVisitor;

import Data.TreeElement;

public interface Visitable {
    int accept(CountVisitor u);
    TreeElement accept(FindVisitor u);
}
