package battleship;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean ocean;
	Ocean ocean1;
	Ocean ocean2;
	ArrayList<Ship> shipArrayList;

	/**
	 * set up method for test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
		shipArrayList = new ArrayList<Ship>();
		shipArrayList = ocean.getAllShips();

		shipArrayList.get(0).placeShipAt(1, 3, true, ocean);
		shipArrayList.get(1).placeShipAt(3, 4, true, ocean);
		shipArrayList.get(2).placeShipAt(3, 2, false, ocean);
		shipArrayList.get(3).placeShipAt(7, 0, false, ocean);
		shipArrayList.get(4).placeShipAt(7, 6, true, ocean);
		shipArrayList.get(5).placeShipAt(6, 9, false, ocean);
		shipArrayList.get(6).placeShipAt(1, 0, false, ocean);
		shipArrayList.get(7).placeShipAt(4, 0, true, ocean);
		shipArrayList.get(8).placeShipAt(7, 2, false, ocean);
		shipArrayList.get(9).placeShipAt(5, 7, true, ocean);

		ocean1 = new Ocean();
	}

	/**
	 * test method for the constructor of ocean {@link battleship.Ocean#Ocean()}.
	 */
	@Test
	public void testOcean() {
		ocean2 = new Ocean();
		assertTrue(ocean2 instanceof Ocean);
		assertTrue(ocean2.getShipArray()[5][5] instanceof EmptySea);
		assertFalse(ocean2.getShadow()[5][5]);
		assertEquals(ocean2.getAllShips().size(),10);
		assertEquals(ocean2.getShotsFired(),0);
		assertEquals(ocean2.getHitCount(),0);	

	}

	/**
	 * test method for {@link battleship.Ocean#isOccupied(int, int)}.
	 */
	@Test
	public void testIsOccupied() {
		assertTrue(ocean.isOccupied(1,0));
		assertTrue(ocean.isOccupied(1,6));
		assertFalse(ocean.isOccupied(1,7));
		assertFalse(ocean.isOccupied(9,9));
	}

	/**
	 * test method for {@link battleship.Ocean#shootAt(int, int)} , {@link battleship.Ocean#getShotsFired()} and {@link battleship.Ocean#getHitCount()}
	 */
	@Test
	public void testShootAndShotHitCount() {
		assertEquals(ocean.getShotsFired(),0);
		assertEquals(ocean.getHitCount(),0);		
		assertFalse(ocean.shootAt(1,1));
		assertFalse(ocean.shootAt(1,1));
		assertEquals(ocean.getShotsFired(),2);
		assertEquals(ocean.getHitCount(),0);
		assertTrue(ocean.shootAt(3,4));
		assertEquals(ocean.getShotsFired(),3);
		assertEquals(ocean.getHitCount(),1);
		assertTrue(ocean.shootAt(3,4));
		assertEquals(ocean.getShotsFired(),4);
		assertEquals(ocean.getHitCount(),2);

		assertTrue(ocean.shootAt(3,5));
		assertFalse(shipArrayList.get(1).isSunk());
		assertTrue(ocean.shootAt(3,6));
		assertTrue(shipArrayList.get(1).isSunk());
		assertEquals(ocean.getShotsFired(),6);
		assertEquals(ocean.getHitCount(),4);
		assertFalse(ocean.shootAt(3,6));
		assertEquals(ocean.getShotsFired(),7);
		assertEquals(ocean.getHitCount(),4);

		assertTrue(ocean.shootAt(1,0));
		assertFalse(ocean.shootAt(1,0));
		assertEquals(ocean.getShotsFired(),9);
		assertEquals(ocean.getHitCount(),5);
		assertFalse(ocean.shootAt(4,4));
	}

	/**
	 *test method for {@link battleship.Ocean#getShipsSunk()}
	 */
	@Test
	public void testGetShipsSunk() {	
		ocean.shootAt(1,0);
		assertEquals(ocean.getShipsSunk(),1);		
		ocean.shootAt(1,3);
		ocean.shootAt(1,4);
		assertEquals(ocean.getShipsSunk(),1);
		ocean.shootAt(1,5);
		assertEquals(ocean.getShipsSunk(),1);
		ocean.shootAt(1,6);
		assertEquals(ocean.getShipsSunk(),2);	
	}

	/**
	 * test method for {@link battleship.Ocean#isGameOver()}
	 */
	@Test
	public void testIsGameOver() {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++){
				ocean.shootAt(i, j);
			}
		}
		assertFalse(ocean.isGameOver());
		ocean.shootAt(6, 9);
		ocean.shootAt(7, 9);
		assertFalse(ocean.isGameOver());
		ocean.shootAt(8, 0);
		assertTrue(ocean.isGameOver());
		ocean.shootAt(8, 1);
		assertTrue(ocean.isGameOver());
	}

	/**
	 * test method for {@link battleship.Ocean#getShipArray()}
	 */
	@Test
	public void testGetShipArray() {
		assertFalse(this.ocean1.getShipArray()[1][3].getShipType().equals("battleship"));
		assertFalse(this.ocean1.getShipArray()[1][5].getShipType().equals("battleship"));

		assertTrue(this.ocean.getShipArray()[1][3].getShipType().equals("battleship"));
		assertTrue(this.ocean.getShipArray()[1][5].getShipType().equals("battleship"));
		assertFalse(this.ocean.getShipArray()[2][5].getShipType().equals("battleship"));
	}

	/**
	 *  test method for {@link battleship.Ocean#setShadow()}
	 *  and {@link battleship.Ocean#getShadow()}
	 */
	@Test
	public void testSetAndGetShadow() {
		ocean1.setShadow();
		assertFalse(ocean1.getShadow()[4][6]);
		assertFalse(ocean1.getShadow()[4][5]);
		assertFalse(ocean1.getShadow()[5][7]);
		ocean1.placeShip(5, 6, shipArrayList.get(0));
		ocean1.placeShip(5, 7, shipArrayList.get(0));
		ocean1.placeShip(5, 8, shipArrayList.get(0));
		ocean1.placeShip(5, 9, shipArrayList.get(0));
		ocean1.setShadow();
		assertTrue(ocean1.getShadow()[4][6]);
		assertTrue(ocean1.getShadow()[4][5]);
		assertTrue(ocean1.getShadow()[5][7]);	
	}

	/**
	 * test method for {@link battleship.Ocean#placeShip(int, int, Ship)}
	 */
	@Test
	public void testPlaceShip() {
		assertFalse(ocean1.getShipArray()[6][4].getShipType().equals("battleship"));
		ocean1.placeShip(6, 4, shipArrayList.get(0));		
		assertTrue(ocean1.getShipArray()[6][4].getShipType().equals("battleship"));
		assertFalse(ocean1.getShipArray()[6][5].getShipType().equals("battleship"));
	}

}