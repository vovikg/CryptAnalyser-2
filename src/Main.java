public class Main {
    public static void main(String[] args) {
        Encryption encryption = new Encryption();

        encryption.encrypt("files/source.txt", "files/destination.txt",3);
        encryption.decryption("files/destination.txt", "files/decryptedFile.txt", 3);

//        encryption.cryptAnalyzer("files/destination.txt","files/CryptAnalyserDestinationFile.txt");
    }
}