# AI-Based Helpdesk System 🛡️

![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Kotlin](https://img.shields.io/badge/Kotlin-2.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.8-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Spring AI](https://img.shields.io/badge/Spring_AI-1.1.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

An intelligent, conversational Helpdesk Agent designed for **RV Tech Support**. The system uses **Spring Boot** and **Spring AI** to leverage Large Language Models (LLMs) with **Function Calling** capabilities. The AI agent, "Liza," interacts directly with the database to create, update, and retrieve support tickets while maintaining a polite and professional persona.

---

## 🤖 The Persona: "Liza"

The core of this system is **Liza**, an AI agent configured with strict operational rules to ensure accurate data handling and professional communication.

| Attribute | Setting |
| :--- | :--- |
| **Role** | RV Tech Support Agent |
| **Tone** | Polite, Empathetic, Professional |
| **Greeting** | "Welcome, I am Liza." |

### ⚙️ System Workflow Logic

Liza operates on a specific state machine defined in the system prompt:

1.  **Status Query:** If the user asks for status, Liza **must** call `findActiveTickets` first.
2.  **Creation/Update:** On successful database changes, Liza triggers an **Email Notification** immediately.
3.  **Data Validation:** Liza will ask the user for missing parameters (e.g., missing Email on ticket creation) before calling tools.
4.  **Closing:** Every interaction ends with: *"Is there anything else I can help you with today?"*

---

## ✨ Key Features

* **Natural Language Processing:** Users can speak naturally (e.g., *"My internet is down, please help"*).
* **Ticket Management:**
    * **Create:** Captures Name, Description, Summary, and Email.
    * **Read:** Fetches specific ticket details or lists all active tickets.
    * **Update:** Modifies status or adds descriptions to existing tickets.
* **Smart Inference:** The AI automatically infers `Priority` and `Category` based on the user's issue description.
* **Notifications:** Integrated email alerts upon ticket creation or updates.

---

## 🛠️ Tech Stack

* **Language:** Kotlin
* **Framework:** Spring Boot 3.x
* **AI Integration:** Spring AI (GenAi)
* **Database:** MySQL
* **Build Tool:** Maven/Gradle

---

## 🧠 AI Tools & Functions

The LLM is mapped to the following Kotlin functions in `TicketDatabaseTool`:

| Tool Name | Description | Inputs |
| :--- | :--- | :--- |
| `createTicketTool` | Creates a new support ticket. | `name`, `description`, `priority`, `category`, `email` |
| `findActiveTickets` | Lists all open/active tickets. | *None* |
| `findTicketById` | Retrieves details of a specific ticket. | `id` |
| `updateTicketTool` | Updates status or description. | `id` (required), `status` (optional), `description` (optional) |
| `sendEmailNotificationTool`| Sends confirmation emails. | `email`, `subject`, `body` |

---

## 🚀 Getting Started

### Prerequisites
* JDK 21 or higher
* Maven or Gradle
* An API Key (OpenAI, Anthropic, or Mistral)

### Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/ramveerk7802/Ai-based-helpdesk-backend.git]
    cd ai-helpdesk-system
    ```

2.  **Configure Environment**
    Update your `application.yml` with your AI provider keys and database settings.
    ```yaml
    spring:
     ai:
       google:
         genai:
           api-key: ${API_KEY} # Your Gemini API Key from Google AI Studio
           chat:
             options:
               model: ${LLM_MODEL_NAME_OR_ID}
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
    ```

3.  **Run the Application**
    ```bash
    ./gradlew bootRun
    ```

---

## 📂 Project Structure

```text
src/main/kotlin/com/company/Ai_Based_Helpdesk
├── controllers      # REST Controllers for Chat/UI
├── entities         # JPA Entities (Ticket, Status, etc.)
├── repositories     # Spring Data Repositories
├── services         # Business Logic (TicketService)
├── tools            # AI Function Call Tools (TicketDatabaseTool)
└── HelpdeskApplication.kt
