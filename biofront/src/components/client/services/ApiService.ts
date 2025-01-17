/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { AuthenticationRequest } from '../models/AuthenticationRequest';
import type { Channel } from '../models/Channel';
import type { ChannelCreationRequest } from '../models/ChannelCreationRequest';
import type { Message } from '../models/Message';
import type { MessageCreationRequest } from '../models/MessageCreationRequest';
import type { UserCreationRequest } from '../models/UserCreationRequest';
import type { UserResponse } from '../models/UserResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class ApiService {
    /**
     * Get channels list
     * Get channels list
     * @returns Channel OK
     * @throws ApiError
     */
    public static getApiChannels(): CancelablePromise<Array<Channel>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/channels',
        });
    }
    /**
     * Create user
     * Create an user
     * @param requestBody
     * @returns any
     * @throws ApiError
     */
    public static postApiChannels(
        requestBody: ChannelCreationRequest,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/channels',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * Health Check
     * Health check for application (secured)
     * @returns void
     * @throws ApiError
     */
    public static getApiHealthCheck(): CancelablePromise<void> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/health_check',
        });
    }
    /**
     * Login
     * Authenticate a user
     * @param requestBody
     * @returns any Authentication token
     * @throws ApiError
     */
    public static postApiLogin(
        requestBody: AuthenticationRequest,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/login',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * Get channel messages
     * Get channel messages
     * @param channelUuid channelUUID
     * @returns Message OK
     * @throws ApiError
     */
    public static getApiMessages(
        channelUuid: string,
    ): CancelablePromise<Array<Message>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/messages',
            query: {
                'channelUUID': channelUuid,
            },
        });
    }
    /**
     * Create message
     * Create a new message
     * @param requestBody
     * @returns any Message creation status.
     * @throws ApiError
     */
    public static postApiMessages(
        requestBody: MessageCreationRequest,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/messages',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * Get user
     * Get an user
     * @param userUuid UserUUID
     * @returns UserResponse OK
     * @throws ApiError
     */
    public static getApiUsers(
        userUuid: string,
    ): CancelablePromise<UserResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/users',
            query: {
                'userUUID': userUuid,
            },
        });
    }
    /**
     * Create user
     * Create an user
     * @param requestBody
     * @returns any User creation status.
     * @throws ApiError
     */
    public static postApiUsers(
        requestBody: UserCreationRequest,
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/users',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
}
