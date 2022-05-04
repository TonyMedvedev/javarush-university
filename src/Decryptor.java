public class Decryptor {

    Alphabet alphabet = new Alphabet();

    private final String getAlphabet = alphabet.getAlphabet();

    public StringBuilder toDecrypt(StringBuilder builder, int key) {

        for (int i = 0; i < builder.length(); i++) {
            char elem = builder.charAt(i);
            elem = decryptChar(elem, key);
            builder.setCharAt(i, elem);
        }
        return builder;
    }

    private char decryptChar(char element, int key) {
        key = reloadKey(key);
        int index = getAlphabet.indexOf(element);
        key = reloadKey(index - key);
        return getAlphabet.charAt(key);
    }

    private int reloadKey(int key) {
        if (key < 0) {
            key = getAlphabet.length() + key % getAlphabet.length();
        } else {
            key = key % getAlphabet.length();
        }
        return key;
    }
}
