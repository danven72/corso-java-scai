package it.scai.formazione.java7;

public class EsempioSuppressedException implements AutoCloseable {

	public void close() throws Exception {
		System.out.println("Autoclosable close");
		throw new Exception("Exception while closing resource");
	}

	public void openResourceAndThrowExcaption() throws Exception {
		System.out.println("Resource open");
		throw new Exception("Exception while opening resourse");
	}
	
	/**
	 */
	public static void main(String[] args) throws Exception {
		
		try (EsempioSuppressedException esempio = new EsempioSuppressedException()) {
			esempio.openResourceAndThrowExcaption();
		}
		catch(Exception ex) {
			//Le exception, anche quelle suppressed vengono catchate
			//System.out.println(ex.getMessage());
			//ex.printStackTrace();
			
			// Con getSuppressed() Si puo' accedere direttamente alle Exception soppresse
			for(Throwable t: ex.getSuppressed()) {
				System.out.println("++++++++++++ "+t.getMessage());
			}
		}
	}
}
