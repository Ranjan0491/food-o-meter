export class ExceptionResponse {
    message: string;
    localDateTime: Date;
    stackTrace: string[];

    constructor(message?: string,
        localDateTime?: Date,
        stackTrace?: string[]) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.stackTrace = stackTrace;
    }
}
