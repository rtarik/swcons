package minesweeper.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import minesweeper.Board;

public class MinesweeperClient {
	
	private static final int DEFAULT_PORT = 4444;
	
	private Socket socket;
	private Board board;
	
	public MinesweeperClient(Socket socket, Board board) {
		this.socket = socket;
		this.board = board;
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
            return "Input should be of the form: look|bye|dig x y|flag x y|deflag x y\n";
        }
        String[] tokens = input.split(" ");
        if (tokens[0].equals("look")) {
            // 'look' request
            return board.look()+"\n";
        } else if (tokens[0].equals("help")) {
            // 'help' request
            return "Valid commands are: look|bye|dig x y|flag x y|deflag x y\n";
        } else if (tokens[0].equals("bye")) {
            // 'bye' request
        	try {
        		socket.close();
        	} catch (IOException ioe) {
        		ioe.printStackTrace();
        		throw new UnsupportedOperationException();
        	}
            return "bye\n";
        } else {
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            if (! board.withinBounds(x, y)) {
            	return "Invalid coordinates!\n";
            }
            if (tokens[0].equals("dig")) {
                // 'dig x y' request
            	if (board.containsBombAt(x, y)) {
            		try {
                		socket.close();
                	} catch (IOException ioe) {
                		ioe.printStackTrace();
                		throw new UnsupportedOperationException();
                	}
            		return "BOOM!\n";
            	} else {
            		board.dig(x, y);
            		return board.look() + "\n";
            	}
                
            } else if (tokens[0].equals("flag")) {
                // 'flag x y' request
                board.flag(x, y);
                return board.look() + "\n";
            } else if (tokens[0].equals("deflag")) {
                // 'deflag x y' request
                board.deflag(x, y);
                return board.look() + "\n";
            }
        }
        throw new UnsupportedOperationException();
    }
}
