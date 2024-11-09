CREATE OR ALTER PROCEDURE [PROJEKT].[GetUsers] 
@Id INT = NULL
AS
BEGIN
	IF @Id IS NULL
		BEGIN
			SELECT * FROM [PROJEKT].[Users]
		END
		ELSE
			SELECT * FROM [PROJEKT].[Users] WHERE PUS_Id = @Id
END
GO
EXEC [PROJEKT].[GetUsers]