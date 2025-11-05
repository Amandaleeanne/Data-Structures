package week10;

    /**
     * A class so that I can have a tuple with mixed classes like in python.
     * Can be nested as a tuple within a tuple within a tuple.
     */
    public class Tuple<T, E>{
         T index1;
         E index2;
         Tuple nestedTuple;

        public Tuple(T paths, E time)
        {
            this.index1 = paths;
            this.index2 = time;
            nestedTuple = null;
        }
        public Tuple(Tuple tuple, E time)
        {
            this.nestedTuple = tuple;
            this.index2 = time;
            this.index1 = null;
        }
        /**
         * Gets item accociated with 1 of the tuple -> (1, 2)
         * Mostly here to get rid of the squiggly yellow line
         * @return {@code T}
         */
        public T getIndex1()
        {
            return this.index1;
        }
        /**
         * Gets item accociated with 2 of the tuple -> (1, 2)
         * Mostly here to get rid of the squiggly yellow line
         * @return {@code E}
         */
        public E getIndex2()
        {
            return this.index2;
        }
        /**
         * returns null if there was no given nested tuple, otherwise returns the nested tuple.
         * @return
         */
        public Tuple getNestedTup()
        {
            return this.nestedTuple;
        }
        /**
         * Easy way to obtain the item accociated with 1 of the nested tuple -> (1, 2)
         * @return
         */
        public Object getNestedIndex1()
        {
            if (this.nestedTuple != null)
                return this.nestedTuple.index1;
            else
                return null;
        }

        /**
         * Easy way to obtain the item accociated with 2 of the nested tuple -> (1, 2)
         * @return
         */
        public Object getNestedIndex2()
        {
            if (this.nestedTuple != null)
                return this.nestedTuple.index2;
            else
                return null;
        }
        
        @Override
        public String toString()
        {
            if (this.nestedTuple == null)
                return "( " + index1.toString() + ", " + index2.toString() + " )"; 
            else
                return "( " + this.nestedTuple.toString() + "," + this.index2.toString() + " )";
        }
    }

