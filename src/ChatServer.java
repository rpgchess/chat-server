import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	public static void main(String[] args) throws IOException {
		new ChatServer(12345).executa();
	}
	private int porta;
	private List<PrintStream> clientes;
	public ChatServer(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
	}
	public void executa() throws IOException {
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Porta 12345 aberta!");
		while (true) {
			Socket	cliente	= servidor.accept();
			System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
			PrintStream	ps = new PrintStream(cliente.getOutputStream());
			this.clientes.add(ps);
			TrataClient tc = new TrataClient(cliente.getInputStream(),this);
			new	Thread(tc).start();
		}
	}
	public void distribuiMensagem(String msg) {
		for (PrintStream cliente : this.clientes) {
			cliente.println(msg);
		}
	}
}