package it.scai.formazione.java7;

//Tutte le classi che implementano AutoClosable possono usare il costrutto try-with-resource
public class EsempioAutoclosable implements AutoCloseable {

	public void close() throws Exception {
		System.out.println("Autoclosable close - resource realesed!");
	}

	public void openResource() throws Exception {
		System.out.println("Resource open");
	}
	
	/**
	 * La finally per richiamare la close() si può omettere :)S
	 */
	public static void main(String[] args) throws Exception {
		try (EsempioAutoclosable esempio = new EsempioAutoclosable()) {
			esempio.openResource();
		}
		
	}
}
