package common.exception;;

/**
 * The AimsException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
// Data coupling: Phương thức contruction AimsException truyền tham số message vào phương thức contructions của lớp RuntimeException
public class AimsException extends RuntimeException {

    public AimsException() {

	}

	public AimsException(String message) {
		super(message);
	}
}