import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class ArchivoDePropiedades {

	private Properties propiedad;
	private String ip ;
	private int puerto;
	private String archivo;
	
	public ArchivoDePropiedades(String archivo) {
		propiedad = new Properties();
		ip = "";
		puerto = 0;
		this.archivo = archivo;
	}
	
	public void lectura() {
		try {
			propiedad.load(new FileInputStream(archivo));
			ip = propiedad.getProperty("IP", "localhost");
			puerto = Integer.parseInt(propiedad.getProperty("PUERTO", "10000"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void escritura(String ip, int puerto) {
		try {
			propiedad.setProperty("IP", ip);
			propiedad.setProperty("PUERTO", "" + puerto);
			
			propiedad.store(new FileOutputStream(archivo), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getIP() {
		return ip;
	}
	
	public int getPuerto() {
		return puerto;
	}

}
