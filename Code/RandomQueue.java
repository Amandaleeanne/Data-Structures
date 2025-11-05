import java.util.Random;
@SuppressWarnings("FieldMayBeFinal")
public class RandomQueue<Item> {
        private Random random;
        private MyArrayList<Item> queueItems;

        public RandomQueue()
        {
            random = new Random();
            queueItems = new MyArrayList<>();
        }

        public int getSize()
        {
            return queueItems.getLength();
        }

        public boolean isEmpty(){
            return  queueItems.getLength() == 0;
        }

        public void enqueue(Item item)
        {
            queueItems.append(item);
        }
        
        public Item dequeue()
        {
            int removalIndex = random.nextInt(0, queueItems.getLength());
            Item removedItem = queueItems.getElement(removalIndex);
            
            queueItems.setElement(removalIndex, queueItems.getElement(queueItems.getLength() - 1));
            queueItems.removeEnd();

            return removedItem;  
        }

        public Item sample()
        {
            return queueItems.getElement(random.nextInt(queueItems.getLength()));
        }
        //For testing pourposes, sadly, O(n):
        @Override
        public String toString()
        {
            int length = queueItems.getLength();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            for (int i = 0; i < length - 1; i++) {
                stringBuilder.append(String.valueOf(queueItems.getElement(i)));
                stringBuilder.append(", ");
            }
            stringBuilder.append(queueItems.getElement(length - 1));
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
}
