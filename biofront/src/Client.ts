//----------------------
// <auto-generated>
//     Generated using the NSwag toolchain v14.2.0.0 (NJsonSchema v11.1.0.0 (Newtonsoft.Json v13.0.0.0)) (http://NSwag.org)
// </auto-generated>
//----------------------

/* tslint:disable */
/* eslint-disable */
// ReSharper disable InconsistentNaming

export class Client {
  private http: { fetch(url: RequestInfo, init?: RequestInit): Promise<Response> };
  private baseUrl: string;
  protected jsonParseReviver: ((key: string, value: any) => any) | undefined = undefined;

  constructor(baseUrl?: string, http?: { fetch(url: RequestInfo, init?: RequestInit): Promise<Response> }) {
    this.http = http ? http : window as any;
    this.baseUrl = baseUrl ?? "http://localhost:9000";
  }

  /**
   * Login
   * @return Authentication token
   */
  postApiLogin(body: any): Promise<{ token: string }> {
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

    return this.http.fetch(url_, options_).then((_response: Response) => {
      return this.processPostApiLogin(_response);
    });
  }


  protected processPostApiLogin(response: Response): Promise<{ token: string }> {
    const status = response.status;
    let _headers: any = {};
    if (response.headers && response.headers.forEach) {
      response.headers.forEach((v: any, k: any) => _headers[k] = v);
    }
    if (status === 200) {
      return response.json().then((_responseText) => {
        return { token: _responseText.token };
      });
    } else if (status !== 200 && status !== 204) {
      return response.text().then((_responseText) => {
        return throwException("An unexpected server error occurred.", status, _responseText, _headers);
      });
    }
    return Promise.resolve({ token: '' });
  }

  /**
   * Create user
   * @return User creation status.
   */
  postApiUsers(body: any): Promise<void> {
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

    return this.http.fetch(url_, options_).then((_response: Response) => {
      return this.processPostApiUsers(_response);
    });
  }

  protected processPostApiUsers(response: Response): Promise<void> {
    const status = response.status;
    let _headers: any = {}; if (response.headers && response.headers.forEach) { response.headers.forEach((v: any, k: any) => _headers[k] = v); }
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

  postApiRefresh(body: { username: string }): Promise<void> {
    let url_ = this.baseUrl + "/api/refresh";
    url_ = url_.replace(/[?&]$/, "");

    const content_ = JSON.stringify(body);

    let options_: RequestInit = {
      body: content_,
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      }
    };

    return this.http.fetch(url_, options_).then((_response: Response) => {
      return this.processPostApiRefresh(_response);
    });
  }

  protected processPostApiRefresh(response: Response): Promise<void> {
    const status = response.status;
    let _headers: any = {};
    if (response.headers && response.headers.forEach) {
      response.headers.forEach((v: any, k: any) => _headers[k] = v);
    }
    if (status === 200) {
      return response.text().then((_responseText) => {
        // Możesz tu dodać logikę zapisu tokenu
        const token = JSON.parse(_responseText).token;
        localStorage.setItem('token', token);
        return;
      });
    } else if (status !== 200 && status !== 204) {
      return response.text().then((_responseText) => {
        return throwException("An unexpected server error occurred.", status, _responseText, _headers);
      });
    }
    return Promise.resolve<void>(null as any);
  }

  // Metoda do obsługi odświeżania tokenu
  refreshToken(username: string): Promise<string> {
    return this.postApiRefresh({ username }).then(() => {
      const token = localStorage.getItem('token');
      if (!token) {
        throw new Error('Token not received');
      }
      return token;
    });
  }



}

export class ApiException extends Error {
  message: string;
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

