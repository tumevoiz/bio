import type {Message, UserResponse} from "@/components/client";

export type MessageWithUser = {
  message: Message,
  user: UserResponse
}
