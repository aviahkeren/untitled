public class HashTable {

    private final static int TABLE_SIZE = 5381;

    LinkedHashEntry[] table;

    HashTable() {
        table = new LinkedHashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public String get(int key, String value) {
        int hash = (key % TABLE_SIZE);
        if (table[hash] == null)
            return null;
        else {
            LinkedHashEntry entry = table[hash];
            while (entry != null && !(entry.getValue().equals(value)))
                entry = entry.getNext();
            if (entry == null)
                return null;
            else
                return entry.getValue();
        }
    }

    public void put(int key, String value) {
        int hash = (key % TABLE_SIZE);
        if (table[hash] == null)
            table[hash] = new LinkedHashEntry(key, value);
        else {
            LinkedHashEntry entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key)
                entry = entry.getNext();
            if (entry.getKey() == key)
                entry.setValue(value);
            else
                entry.setNext(new LinkedHashEntry(key, value));
        }
    }

    public void remove(int key) {
        int hash = (key % TABLE_SIZE);
        if (table[hash] != null) {
            LinkedHashEntry prevEntry = null;
            LinkedHashEntry entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                prevEntry = entry;
                entry = entry.getNext();
            }

            if (entry.getKey() == key) {
                if (prevEntry == null)
                    table[hash] = entry.getNext();
                else
                    prevEntry.setNext(entry.getNext());
            }
        }
    }
}