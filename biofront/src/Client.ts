//----------------------
// <auto-generated>
//     Generated using the NSwag toolchain v14.2.0.0 (NJsonSchema v11.1.0.0 (Newtonsoft.Json v13.0.0.0)) (http://NSwag.org)
// </auto-generated>
//----------------------

import { ClientBase } from "./ClientBase";

/* tslint:disable */
/* eslint-disable */
// ReSharper disable InconsistentNaming

export class Client extends ClientBase {
    private http: { fetch(url: RequestInfo, init?: RequestInit): Promise<Response> };
    private baseUrl: string;
    protected jsonParseReviver: ((key: string, value: any) => any) | undefined = undefined;

    constructor(baseUrl?: string, http?: { fetch(url: RequestInfo, init?: RequestInit): Promise<Response> }) {
        super();
        this.http = http ? http : window as any;
        this.baseUrl = this.getBaseUrl("http://localhost:9000", baseUrl);
    }

    /**
     * Get channels list
     * @return OK
     */
    getApiChannels(): Promise<Channel[]> {
        let url_ = this.baseUrl + "/api/channels";
        url_ = url_.replace(/[?&]$/, "");

        let options_: RequestInit = {
            method: "GET",
            headers: {
                "Accept": "application/json"
            }
        };

        return this.transformOptions(options_).then(transformedOptions_ => {
            return this.http.fetch(url_, transformedOptions_);
        }).then((_response: Response) => {
            return this.transformResult(url_, _response, (_response: Response) => this.processGetApiChannels(_response));
        });
    }

    protected processGetApiChannels(response: Response): Promise<Channel[]> {
        const status = response.status;
        let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); };
        if (status === 200) {
            return response.text().then((_responseText) => {
                let result200: any = null;
                let resultData200 = _responseText === "" ? null : JSON.parse(_responseText, this.jsonParseReviver);
                if (Array.isArray(resultData200)) {
                    result200 = [] as any;
                    for (let item of resultData200)
                        result200!.push(Channel.fromJS(item));
                }
                else {
                    result200 = <any>null;
                }
                return result200;
            });
        } else if (status !== 200 && status !== 204) {
            return response.text().then((_responseText) => {
                return throwException("An unexpected server error occurred.", status, _responseText, _headers);
            });
        }
        return Promise.resolve<Channel[]>(null as any);
    }

    /**
     * Create user
     */
    postApiChannels(body: ChannelCreationRequest): Promise<void> {
        let url_ = this.baseUrl + "/api/channels";
        url_ = url_.replace(/[?&]$/, "");

        const content_ = JSON.stringify(body);

        let options_: RequestInit = {
            body: content_,
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            }
        };

        return this.transformOptions(options_).then(transformedOptions_ => {
            return this.http.fetch(url_, transformedOptions_);
        }).then((_response: Response) => {
            return this.transformResult(url_, _response, (_response: Response) => this.processPostApiChannels(_response));
        });
    }

    protected processPostApiChannels(response: Response): Promise<void> {
        const status = response.status;
        let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); };
        if (status === 201) {
            return response.text().then((_responseText) => {
                return;
            });
        } else if (status !== 200 && status !== 204) {
            return response.text().then((_responseText) => {
                return throwException("An unexpected server error occurred.", status, _responseText, _headers);
            });
        }
        return Promise.resolve<void>(null as any);
    }

    /**
     * Login
     * @return Authentication token
     */
    postApiLogin(body: AuthenticationRequest): Promise<void> {
        let url_ = this.baseUrl + "/api/login";
        url_ = url_.replace(/[?&]$/, "");

        const content_ = JSON.stringify(body);

        let options_: RequestInit = {
            body: content_,
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            }
        };

        return this.transformOptions(options_).then(transformedOptions_ => {
            return this.http.fetch(url_, transformedOptions_);
        }).then((_response: Response) => {
            return this.transformResult(url_, _response, (_response: Response) => this.processPostApiLogin(_response));
        });
    }

    protected processPostApiLogin(response: Response): Promise<void> {
        const status = response.status;
        let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); };
        if (status === 200) {
            return response.text().then((_responseText) => {
                return;
            });
        } else if (status !== 200 && status !== 204) {
            return response.text().then((_responseText) => {
                return throwException("An unexpected server error occurred.", status, _responseText, _headers);
            });
        }
        return Promise.resolve<void>(null as any);
    }

    /**
     * Create message
     * @return Message creation status.
     */
    postApiMessages(body: MessageCreationRequest): Promise<void> {
        let url_ = this.baseUrl + "/api/messages";
        url_ = url_.replace(/[?&]$/, "");

        const content_ = JSON.stringify(body);

        let options_: RequestInit = {
            body: content_,
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            }
        };

        return this.transformOptions(options_).then(transformedOptions_ => {
            return this.http.fetch(url_, transformedOptions_);
        }).then((_response: Response) => {
            return this.transformResult(url_, _response, (_response: Response) => this.processPostApiMessages(_response));
        });
    }

    protected processPostApiMessages(response: Response): Promise<void> {
        const status = response.status;
        let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); };
        if (status === 200) {
            return response.text().then((_responseText) => {
                return;
            });
        } else if (status !== 200 && status !== 204) {
            return response.text().then((_responseText) => {
                return throwException("An unexpected server error occurred.", status, _responseText, _headers);
            });
        }
        return Promise.resolve<void>(null as any);
    }

    /**
     * Create user
     * @return User creation status.
     */
    postApiUsers(body: UserCreationRequest): Promise<void> {
        let url_ = this.baseUrl + "/api/users";
        url_ = url_.replace(/[?&]$/, "");

        const content_ = JSON.stringify(body);

        let options_: RequestInit = {
            body: content_,
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            }
        };

        return this.transformOptions(options_).then(transformedOptions_ => {
            return this.http.fetch(url_, transformedOptions_);
        }).then((_response: Response) => {
            return this.transformResult(url_, _response, (_response: Response) => this.processPostApiUsers(_response));
        });
    }

    protected processPostApiUsers(response: Response): Promise<void> {
        const status = response.status;
        let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); };
        if (status === 200) {
            return response.text().then((_responseText) => {
                return;
            });
        } else if (status !== 200 && status !== 204) {
            return response.text().then((_responseText) => {
                return throwException("An unexpected server error occurred.", status, _responseText, _headers);
            });
        }
        return Promise.resolve<void>(null as any);
    }
}

export class AuthenticationRequest implements IAuthenticationRequest {
    username!: string;
    password!: PlaintextPassword;

    [key: string]: any;

    constructor(data?: IAuthenticationRequest) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
        if (!data) {
            this.password = new PlaintextPassword();
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.username = _data["username"];
            this.password = _data["password"] ? PlaintextPassword.fromJS(_data["password"]) : new PlaintextPassword();
        }
    }

    static fromJS(data: any): AuthenticationRequest {
        data = typeof data === 'object' ? data : {};
        let result = new AuthenticationRequest();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["username"] = this.username;
        data["password"] = this.password ? this.password.toJSON() : <any>undefined;
        return data;
    }
}

export interface IAuthenticationRequest {
    username: string;
    password: PlaintextPassword;

    [key: string]: any;
}

export class PlaintextPassword implements IPlaintextPassword {
    value!: string;

    [key: string]: any;

    constructor(data?: IPlaintextPassword) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.value = _data["value"];
        }
    }

    static fromJS(data: any): PlaintextPassword {
        data = typeof data === 'object' ? data : {};
        let result = new PlaintextPassword();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["value"] = this.value;
        return data;
    }
}

export interface IPlaintextPassword {
    value: string;

    [key: string]: any;
}

export class UserCreationRequest implements IUserCreationRequest {
    username!: string;
    password!: PlaintextPassword;

    [key: string]: any;

    constructor(data?: IUserCreationRequest) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
        if (!data) {
            this.password = new PlaintextPassword();
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.username = _data["username"];
            this.password = _data["password"] ? PlaintextPassword.fromJS(_data["password"]) : new PlaintextPassword();
        }
    }

    static fromJS(data: any): UserCreationRequest {
        data = typeof data === 'object' ? data : {};
        let result = new UserCreationRequest();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["username"] = this.username;
        data["password"] = this.password ? this.password.toJSON() : <any>undefined;
        return data;
    }
}

export interface IUserCreationRequest {
    username: string;
    password: PlaintextPassword;

    [key: string]: any;
}

export class ChannelCreationRequest implements IChannelCreationRequest {
    name!: string;

    [key: string]: any;

    constructor(data?: IChannelCreationRequest) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.name = _data["name"];
        }
    }

    static fromJS(data: any): ChannelCreationRequest {
        data = typeof data === 'object' ? data : {};
        let result = new ChannelCreationRequest();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["name"] = this.name;
        return data;
    }
}

export interface IChannelCreationRequest {
    name: string;

    [key: string]: any;
}

export class Channel implements IChannel {
    id!: string;
    name!: string;

    [key: string]: any;

    constructor(data?: IChannel) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.id = _data["id"];
            this.name = _data["name"];
        }
    }

    static fromJS(data: any): Channel {
        data = typeof data === 'object' ? data : {};
        let result = new Channel();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["id"] = this.id;
        data["name"] = this.name;
        return data;
    }
}

export interface IChannel {
    id: string;
    name: string;

    [key: string]: any;
}

export class MessageCreationRequest implements IMessageCreationRequest {
    channelId!: string;
    userId!: string;
    message!: string;

    [key: string]: any;

    constructor(data?: IMessageCreationRequest) {
        if (data) {
            for (var property in data) {
                if (data.hasOwnProperty(property))
                    (<any>this)[property] = (<any>data)[property];
            }
        }
    }

    init(_data?: any) {
        if (_data) {
            for (var property in _data) {
                if (_data.hasOwnProperty(property))
                    this[property] = _data[property];
            }
            this.channelId = _data["channelId"];
            this.userId = _data["userId"];
            this.message = _data["message"];
        }
    }

    static fromJS(data: any): MessageCreationRequest {
        data = typeof data === 'object' ? data : {};
        let result = new MessageCreationRequest();
        result.init(data);
        return result;
    }

    toJSON(data?: any) {
        data = typeof data === 'object' ? data : {};
        for (var property in this) {
            if (this.hasOwnProperty(property))
                data[property] = this[property];
        }
        data["channelId"] = this.channelId;
        data["userId"] = this.userId;
        data["message"] = this.message;
        return data;
    }
}

export interface IMessageCreationRequest {
    channelId: string;
    userId: string;
    message: string;

    [key: string]: any;
}

export class ApiException extends Error {
    override message: string;
    status: number;
    response: string;
    headers: { [key: string]: any; };
    result: any;

    constructor(message: string, status: number, response: string, headers: { [key: string]: any; }, result: any) {
        super();

        this.message = message;
        this.status = status;
        this.response = response;
        this.headers = headers;
        this.result = result;
    }

    protected isApiException = true;

    static isApiException(obj: any): obj is ApiException {
        return obj.isApiException === true;
    }
}

function throwException(message: string, status: number, response: string, headers: { [key: string]: any; }, result?: any): any {
    if (result !== null && result !== undefined)
        throw result;
    else
        throw new ApiException(message, status, response, headers, null);
}