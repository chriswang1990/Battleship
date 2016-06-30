package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShipTest {
	private Ship battleship;
	private Ship cruiser;
	private Ship destroyer;
	private Ship submarine;
	private Ocean ocean;
	private EmptySea emptySea;

	/**
	 * SetUp method for test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.battleship = new Battleship();
		this.cruiser = new Cruiser();
		this.destroyer = new Destroyer();
		this.submarine = new Submarine();
		this.ocean = new Ocean();
		emptySea = new EmptySea();
	}

	/**
	 * Test method for {@link battleship.Ship#setBowRow(int)}
	 * and {@link battleship.Ship#getBowRow()}
	 * and {@link battleship.Ship#setBowColumn(int)}
	 * and {@link battleship.Ship#getBowColumn()}
	 * and {@link battleship.Ship#setHorizontal(boolean)}
	 * and {@link battleship.Ship#isHorizontal()}
	 */
	@Test
	public void testShip() {
		battleship.setBowRow(1);
		int bowRow = battleship.getBowRow();
		assertEquals(1, bowRow);

		cruiser.setBowColumn(2);
		int bowColumn = cruiser.getBowColumn();
		assertEquals(2, bowColumn);

		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());
		submarine.setHorizontal(false);
		assertFalse(submarine.isHorizontal());
	}

	/**
	 * Test method for {@link battleship.Ship#getShipType()}
	 */
	@Test
	public void testGetShipType(){
		assertEquals("battleship", battleship.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
		assertEquals("submarine", submarine.getShipType());
	}

	/**
	 * Test method for {@link battleship.Ship#getLength()}
	 */
	@Test
	public void testGetLength() {
		assertEquals(4, battleship.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(1, emptySea.getLength());
	}

	/**
	 * Test method for {@link battleship.Ship#okToPlaceShipAt(int, int, boolean, Ocean)}
	 * and {@link battleship.Ship#placeShipAt(int, int, boolean, Ocean)}
	 */
	@Test
	public void testOkToPlaceShipAt() {
		boolean place0 = battleship.okToPlaceShipAt(3, 4, true, ocean);
		boolean place1 = battleship.okToPlaceShipAt(2, 7, true, ocean);
		boolean place2 = battleship.okToPlaceShipAt(7, 0, false, ocean);
		assertTrue(place0);
		assertFalse(place1||place2);
		battleship.placeShipAt(3, 4, true, ocean);
		boolean place3 = cruiser.okToPlaceShipAt(2, 1, true, ocean);
		boolean place4 = cruiser.okToPlaceShipAt(4, 1, true, ocean);
		boolean place5 = cruiser.okToPlaceShipAt(2, 8, true, ocean);
		boolean place6 = cruiser.okToPlaceShipAt(4, 8, true, ocean);
		boolean place7 = cruiser.okToPlaceShipAt(0, 3, false, ocean);
		boolean place8 = cruiser.okToPlaceShipAt(4, 3, false, ocean);
		boolean place9 = cruiser.okToPlaceShipAt(0, 8, false, ocean);
		boolean place10 = cruiser.okToPlaceShipAt(4, 8, false, ocean);
		boolean place11 = cruiser.okToPlaceShipAt(5, 6, false, ocean);
		assertFalse(place3||place4||place5||place6||place7||place8||place9||place10);
		assertTrue(place11);
		cruiser.placeShipAt(5, 6, false, ocean);

		boolean place12 = destroyer.okToPlaceShipAt(8, 7, false, ocean);
		boolean place13 = destroyer.okToPlaceShipAt(0, 1, true, ocean);
		assertFalse(place12);
		assertTrue(place13);
		destroyer.placeShipAt(0, 1, true, ocean);

		boolean place14 = submarine.okToPlaceShipAt(1, 0, false, ocean);
		boolean place15 = submarine.okToPlaceShipAt(0, 9, true, ocean);


		submarine.placeShipAt(0, 9, false, ocean);
		assertFalse(place14);
		assertTrue(place15);


	}

	/**
	 * Test method for {@link battleship.Ship#shootAt(int, int)}
	 * and {@link battleship.Ship#getHitArray()}
	 * and {@link battleship.Ship#isSunk()}
	 */
	@Test
	public void testShootHitSunk() {
		Cruiser cruiser1 = new Cruiser();
		cruiser1.setBowRow(2);
		cruiser1.setBowColumn(3);
		cruiser1.setHorizontal(true);

		//shoot at the middle of the cruiser
		assertFalse(cruiser1.shootAt(1, 7));	
		assertTrue(cruiser1.shootAt(2, 4));
		assertFalse(cruiser1.getHitArray()[0]);
		assertTrue(cruiser1.getHitArray()[1]);
		assertFalse(cruiser1.getHitArray()[2]);
		assertFalse(cruiser1.isSunk());

		//shoot at all the ship
		assertTrue(cruiser1.shootAt(2, 3));
		assertTrue(cruiser1.shootAt(2, 3));
		assertTrue(cruiser1.shootAt(2, 5));
		assertTrue(cruiser1.getHitArray()[0]);
		assertTrue(cruiser1.getHitArray()[1]);
		assertTrue(cruiser1.getHitArray()[2]);
		assertTrue(cruiser1.isSunk());
		assertFalse(cruiser1.shootAt(2, 3));

	}

	/**
	 * Test method for {@link battleship.Ship#toString}
	 */
	@Test
	public void testToString() {
		Submarine submarine1 = new Submarine();
		submarine1.setBowRow(2);
		submarine1.setBowColumn(3);
		submarine1.setHorizontal(true);
		assertEquals("S",submarine1.toString());
		submarine1.shootAt(2, 3);
		assertEquals("x",submarine1.toString());
		assertEquals("-",emptySea.toString());
	}

}
