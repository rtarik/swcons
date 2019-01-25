package minesweeper.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MinesweeperClient {
	
	private static final int DEFAULT_PORT = 4444;
	
	private Socket socket;
	
	public MinesweeperClient(Socket socket) {
		this.socket = socket;
	}
	
	public void connect() throws IOException {
		try {
			handleConnection(socket);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			socket.close();
		}	
	}
	
	/**
     * Handle a single client connection. Returns when client disconnects.
     * 
     * @param socket socket where the client is connected
     * @throws IOException if the connection encounters an error or terminates unexpectedly
     */
    private void handleConnection(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String output = handleRequest(line);
                if (output != null) {
                    // TODO: Consider improving spec of handleRequest to avoid use of null
                    out.println(output);
                }
            }
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Handler for client input, performing requested operations and returning an output message.
     * 
     * @param input message from client
     * @return message to client, or null if none
     */
    private String handleRequest(String input) {
        String regex = "(look)|(help)|(bye)|"
                     + "(dig -?\\d+ -?\\d+)|(flag -?\\d+ -?\\d+)|(deflag -?\\d+ -?\\d+)";
        if ( ! input.matches(regex)) {
            // invalid input
            // TODO Problem 5
        }
        String[] tokens = input.split(" ");
        if (tokens[0].equals("look")) {
            // 'look' request
            // TODO Problem 5
        } else if (tokens[0].equals("help")) {
            // 'help' request
            // TODO Problem 5
        } else if (tokens[0].equals("bye")) {
            // 'bye' request
            // TODO Problem 5
        } else {
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            if (tokens[0].equals("dig")) {
                // 'dig x y' request
                // TODO Problem 5
            } else if (tokens[0].equals("flag")) {
                // 'flag x y' request
                // TODO Problem 5
            } else if (tokens[0].equals("deflag")) {
                // 'deflag x y' request
                // TODO Problem 5
            }
        }
        // TODO: Should never get here, make sure to return in each of the cases above
        throw new UnsupportedOperationException();
    }
}
