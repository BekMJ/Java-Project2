import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBlocks {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void testReadFile() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		ArrayList<Blocks> testBlocks = Blocks.getBlocks();
		
		assertEquals(100, testBlocks.size());
		assertEquals(15049330, testBlocks.get(49).getNumber());
		assertEquals("0xcd458d7f11023556cc9058f729831a038cb8df9c", testBlocks.get(80).getMiner());
	}
	
	@Test
	void testSortBlocksByNumber() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		Blocks.sortBlocksByNumber();
		ArrayList<Blocks> b = Blocks.getBlocks();
		assertEquals(15049314, b.get(6).getNumber());
		assertEquals("0xc730b028da66ebb14f20e67c68dd809fbc49890d", b.get(10).getMiner());
	}
	
	@Test
	void testGetDate() {
		Blocks blockA = new Blocks(1, "0x0000", 1670816881, 100);
		String date = blockA.getDate();
		assertEquals(("Sun, 11 December 2022 21:48:01 CST"), date);
	}
	
	@Test
	void testToString() {
		Blocks blockA = new Blocks();
		Blocks blockB = new Blocks(123456);
		Blocks blockC = new Blocks(654321, "0xabcdef");
		Blocks blockD = new Blocks(123, "0x0000", 1111, 100);
		System.out.println(blockA);
		assertTrue(outputStreamCaptor.toString().contains("Empty Block"));
		System.out.println(blockB);
		assertTrue(outputStreamCaptor.toString().contains("Block Number: 123456"));
		System.out.println(blockC);
		assertTrue(outputStreamCaptor.toString().contains("Block Number: 654321 Miner Address: 0xabcdef"));
		System.out.println(blockD);
		assertTrue(outputStreamCaptor.toString().contains("Block Number: 123 Miner Address: 0x0000"));
	}
	
	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}
	
	@Test
	void testConstructors() {
		int number = 01234567;
		String miner = "0x89abcdef";
		long timestamp = 1000000;
		int transactions = 100;
		
		Blocks testCon1 = new Blocks();
		assertEquals(0, testCon1.getNumber());
		assertNull(testCon1.getMiner());
		
		Blocks testCon2 = new Blocks(number);
		assertEquals(number, testCon2.getNumber());
		assertNull(testCon2.getMiner());
		
		Blocks testCon3 = new Blocks(number, miner);
		assertEquals(number, testCon3.getNumber());
		assertEquals(miner, testCon3.getMiner());
		
		Blocks testCon4 = new Blocks(number, miner, timestamp, transactions);
		assertEquals(transactions, testCon4.getTransactions());
	}
	
	@Test
	void testTransactionDiff() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		Blocks.sortBlocksByNumber();
		
		int count = Blocks.transactionDiff(Blocks.getBlockByNumber(15049308), Blocks.getBlockByNumber(15049407));
		assertEquals(18991, count);
		count = Blocks.transactionDiff(Blocks.getBlockByNumber(15), Blocks.getBlockByNumber(15049406));
		assertEquals(-1, count);
		count = Blocks.transactionDiff(Blocks.getBlockByNumber(15049406), Blocks.getBlockByNumber(15049404));
		assertEquals(-1, count);
		count = Blocks.transactionDiff(Blocks.getBlockByNumber(15049406), Blocks.getBlockByNumber(15049406));
		assertEquals(-1, count);

	}
	
	@Test
	void testTimeDiff() throws FileNotFoundException, IOException {
		Blocks A = new Blocks(1, "0x0000", 60, 5);
		Blocks B = new Blocks(2, "0x0001", 120, 10);
		Blocks C = new Blocks(3, "0x0002", 3668, 4);
		Blocks D = new Blocks(4, "0x0003", 34589, 6);
		
		Blocks.timeDiff(A, B);
		assertTrue(outputStreamCaptor.toString().contains("The difference in time between Block 1 and Block 2 is 0 hours, 1 minute, and 0 seconds."));
		Blocks.timeDiff(B, A);
		assertTrue(outputStreamCaptor.toString().contains("The difference in time between Block 2 and Block 1 is 0 hours, 1 minute, and 0 seconds."));
		Blocks.timeDiff(A, C);
		assertTrue(outputStreamCaptor.toString().contains("The difference in time between Block 1 and Block 3 is 1 hour, 0 minutes, and 8 seconds."));
		Blocks.timeDiff(A, D);
		assertTrue(outputStreamCaptor.toString().contains("The difference in time between Block 1 and Block 4 is 9 hours, 35 minutes, and 29 seconds."));

		Blocks.timeDiff(Blocks.getBlockByNumber(100), D);
		assertTrue(outputStreamCaptor.toString().contains("A given Block is null."));

	}
	
	@Test
	void testGetBlocksEncapsulation() throws FileNotFoundException, IOException {
		Blocks.readFile("ethereumP1data.txt");
		ArrayList<Blocks> b = Blocks.getBlocks();
		
		b.remove(0);
		
		b = Blocks.getBlocks();
		assertEquals(100, b.size());
		assertEquals(15049308, b.get(0).getNumber());
		
	}

}
