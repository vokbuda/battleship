package com.battlefield.settings;

public enum Status {
	CellIsOutOfRange(Words.CellIsOutOfRange),
	NullObject(Words.NullObject),
	OK(Words.OK),
	IsNotPossibleAddShip(Words.IsNotPossibleAddShip),
	IsNotPossibleModifyShip(Words.IsNotPossibleModifyShip),
	isNoAppropriateShip(Words.isNoAppropriateShip),
	AttemptIsNotSuccessful(Words.AttemptIsNotSuccessful),
	ProblemCell(Words.ProblemCell),
	NumberIsNotValid(Words.NumberIsNotValid),
	IsNoShip(Words.IsNoShip),
	UserEliminated(Words.UserEliminated),
	NobodyEliminated(Words.NobodyEliminated),
	ComputerEliminated(Words.ComputerEliminated),
	ComputerNotFound(Words.ComputerNotFound),
	UserAlreadyExists(Words.UserAlreadyExists),
	StartGame(Words.StartGame),
	NextTurn(Words.NextTurn),
	UserLimit(Words.UserLimit),
	ComputerLimit(Words.ComputerLimit),
	UserIsNotFound(Words.UserIsNotFound);	
	Status(String state) {
		this.state=state;
	}
	public String getResult() {
		return this.state;
	}
	private String state;

}
