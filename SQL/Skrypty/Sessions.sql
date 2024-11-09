CREATE TABLE [PROJEKT].[Sessions]
(
	PSS_Id INT IDENTITY(1,1) PRIMARY KEY,
	PSS_UserId INT,
	PSS_Token VARCHAR(MAX),
	PSS_ExpirationDate DATETIME
)