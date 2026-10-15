<div align="center">

# 🤖 AI Chatbot

### A fast, friendly AI assistant for Android, powered by Groq

![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![IDE](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)
![Groq](https://img.shields.io/badge/Powered_by-Groq-F55036?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-6750A4?style=for-the-badge)

*Ask anything. Get answers in a blink.* ⚡

</div>

---
<img width="72" height="72" alt="HELLO" src="https://github.com/user-attachments/assets/def75d1a-02aa-4fa3-8bd4-6d73e4b408d6" />

## ✨ Features

- 💬 **Instant chat**: clean message bubbles with a smooth, familiar layout
- ⚡ **Blazing-fast replies** using Groq's high-speed inference
- 🎤 **Voice input**: tap the mic and speak your message
- 🗑️ **One-tap clear**: wipe the conversation from the top bar
- 🚨 **Friendly error messages**: see what went wrong right in the chat
- 🎨 **Material design**: a purple theme that feels at home on Android

## 🧠 Model

The app talks to Groq's OpenAI-compatible API using:

| Setting  | Value                                  |
| -------- | -------------------------------------- |
| Model    | `openai/gpt-oss-20b`                   |
| Endpoint | `https://api.groq.com/openai/v1`       |

> 💡 Want smarter answers? Swap the model for `openai/gpt-oss-120b`. See the [Groq model list](https://console.groq.com/docs/models) for what your key can use.

## 🚀 Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (latest stable)
- An Android device or emulator
- A free API key from [Groq Console](https://console.groq.com/keys)

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```

**2. Add your API key**

Open (or create) `local.properties` in the project root and add:

```properties
GROQ_API_KEY=your_api_key_here
```

> 🔒 `local.properties` is git-ignored, so your key never leaves your machine.

**3. Run the app**

Open the project in Android Studio, let Gradle sync, then press **Run ▶️**.

## 🛠️ Troubleshooting

| Problem | Fix |
| ------- | --- |
| `The model ... does not exist or you do not have access to it` | Your key can't use that model. Switch to `openai/gpt-oss-20b` or check the [model list](https://console.groq.com/docs/models). |
| `401 Unauthorized` | The API key is missing or wrong. Re-check `local.properties` and sync Gradle. |
| App can't reach the server | Check your internet connection and make sure the `INTERNET` permission is in `AndroidManifest.xml`. |

## 🔐 Security

Never commit your API key. If you accidentally push one, delete it at [console.groq.com/keys](https://console.groq.com/keys) and create a new one right away.

## 🗺️ Roadmap

- [ ] Streaming responses (typing effect)
- [ ] Chat history saved on device
- [ ] Dark mode
- [ ] Model picker in settings
- [ ] Markdown rendering in replies

## 🤝 Contributing

Contributions are welcome!

1. Fork the repo
2. Create your branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m "Add amazing feature"`
4. Push: `git push origin feature/amazing-feature`
5. Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.

---

<div align="center">

Made with ❤️ and a lot of ☕ using Android Studio

⭐ **If you like this project, give it a star!** ⭐

</div>
