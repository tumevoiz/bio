import { Client } from "./Client";

export class ClientBase {
    getAuthorization(): string {
        return '';
    }
    protected getBaseUrl(arg0: string, baseUrl: string | undefined): string {
        return 'http://localhost:9000';
    }

    protected transformOptions = (options: RequestInit): Promise<RequestInit> => {
        options.headers = {
            ...options.headers,
            // Authorization: this.getAuthorization(),
        };
        return Promise.resolve(options);
    };

    protected transformResult(url: string, response: Response, processor: (response: Response) => any) {
        return processor(response);
    }
}