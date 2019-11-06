import java.io.InputStream;
import java.util.Scanner;

public class TrataClient implements Runnable {
	private InputStream cliente;
	private ChatServer servidor;
	public TrataClient(InputStream cliente, ChatServer servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}
	public void run() {
		Scanner s = new Scanner(this.cliente);
		while (s.hasNextLine())
			servidor.distribuiMensagem(s.nextLine());
		s.close();
	}
}