# fullstack_capstone

💻CODIFY</>💻
🌐 INDIRIZZO HTTP BACK-END localhost:3001 // Nome Database: CODIFY_CAPSTONE
💥💥💥 Repository: https://github.com/Marcotmn/fullstack_capstone 💥💥💥

📝 DESCRIZIONE
"CODIFY</>" è una piattaforma Full-Stack realizzata con Angular e Java Spring che funge da portale per mettere in contatto diretto aziende e privati che cercano collaborazioni occasionali con gli sviluppatori per la realizzazione di applicazioni web, mobile, desktop o per interventi di bug fixing, manutenzione, aggiornamento o implementazione di codice su applicativi già esistenti. Aziende e privati hanno la possibilità di pubblicare annunci dove descrivono il tipo di applicazione o intervento di cui hanno bisogno, gli sviluppatori inviano delle proposte. Se l'azienda o il privato accettano la proposta, lo sviluppatore esegue il lavoro.

*******************************
🌟 FUNZIONALITA

🔒 Autenticazione Utente: Gli utenti devono autenticarsi per accedere alle funzionalità complete dell'applicazione.
🖥 Bacheca annunci: Gli utenti possono esplorare gli annunci raccolti nella homepage "bacheca annunci" e, nel caso di utenti aziende e privati, pubblicarne di nuovi; solo gli utenti sviluppatori possono rispondere agli annunci con le proposte.
🔍 Filtro: Gli utenti possono filtrare gli annunci per categoria.
📖 Dettagli annunci: ogni utente può visualizzare i dettagli degli annunci semplicemente facendo click nella bacheca annunci; nel caso di aziende e privati, nel dettaglio dell'annuncio, potranno visualizzare una scheda con tutte le proposte ricevute per quel determinato annuncio e decidere se accettare o rifiutare le proposte. 
👤 Profilo Utente: Gli utenti possono visualizzare e gestire i propri annunci e proposte direttamente dalla sezione profilo utente.

********************************
****FUTURE IMPLEMENTAZIONI****

🔍 Filtri: verranno aggiunti ulteriori criteri di filtraggio degli annunci nella bacheca.
💲 Sistema di pagamento: verrà implementato un sistema di pagamento interno e uno di terze parti (es PayPal).
💬 Recensioni: al termine del lavoro, aziende e privati potranno valutare lo sviluppatore con cui hanno collaborato.
🔝 Le recensioni serviranno all'implementazione di una pagina di ranking dove tutti gli sviluppatori registrati al sito verranno mostrati in ordine di qualità (da ⭐️⭐️⭐️⭐️⭐️ a ⭐️).
📝 Sezione News e forum: verrà implementata una sezione news per la pubblicazione di notizie del mondo digital e tech, e un forum con diverse sezioni.

********************************
****TECNOLOGIE UTILIZZATE****

🎨 Frontend: Angular
⚙️ Backend: Java Spring
🔧 Altri Tool: Eclipse, VScode, Postman, PgAdmin

********************************
💾 INSTALLAZIONE ED USO
Configurazione Angular:

Prima di eseguire l'applicazione "CODIFY</>", assicurati di avere installato Node.js, npm e Angular CLI sul tuo sistema. Se non li hai già installati, ecco come fare:

Prerequisiti
Node.js e npm: Angular richiede Node.js e npm per eseguire e gestire le dipendenze.
Passaggi per l'installazione
Installare Node.js e npm:

Vai al sito web di Node.js: https://nodejs.org/
Scarica e installa la versione LTS di Node.js seguendo le istruzioni sul sito web.
Verifica che l'installazione sia stata eseguita correttamente aprendo un terminale o prompt dei comandi e digitando:
node -v
npm -v
Questi comandi mostreranno le versioni di Node.js e npm installate.
Installare Angular CLI:

Apri un terminale o prompt dei comandi.
Esegui il seguente comando per installare Angular CLI globalmente sul tuo sistema:
npm install -g @angular/cli
Verifica che Angular CLI sia stato installato correttamente con il comando:
ng --version
Installare le dipendenze del progetto:

Naviga nella directory principale del progetto usando il terminale.
Esegui il comando:
npm install
Questo comando installerà tutte le dipendenze necessarie del progetto, inclusi Bootstrap e Bootstrap Icons se sono elencati nel file package.json.
Ora sei pronto per eseguire l'applicazione sul tuo sistema locale.

🚀 Esecuzione dell'applicazione
Naviga nella directory principale del progetto (dove si trova il file angular.json) usando il terminale.

Esegui il comando:

ng s -o
Installazione di bootstrap e bootstrap icon

Esegui il seguente comando per aggiungere Bootstrap al tuo progetto:
npm install bootstrap
Dopo l'installazione, assicurati di aggiungere il riferimento al file CSS di Bootstrap nel file angular.json o styles.css del tuo progetto Angular. Ad esempio, in angular.json:

"styles": [
  "node_modules/bootstrap/dist/css/bootstrap.min.css",
  "src/styles.css"
],
Esegui il seguente comando per aggiungere Bootstrap-icon al tuo progetto:
npm install bootstrap

***************************************
Installazione del Progetto Java Spring
Prerequisiti
Avere installato Java JDK (versione 8 o superiore).
Avere installato Maven (si consiglia l'ultima versione disponibile).

***************************************
Passaggi per l'installazione
Scaricare il Progetto:

Clona o scarica il progetto dal repository GitHub ( **** https://github.com/Marcotmn/fullstack_capstone **** ) su una directory del tuo computer.
Navigare nella Directory del Progetto:

Apri un terminale e naviga nella directory in cui hai clonato o scaricato il progetto.

Installare le Dipendenze:

Esegui il comando seguente per installare tutte le dipendenze necessarie specificate nel pom.xml del progetto:
mvn clean install

***************************************
Eseguire il Progetto:

Esegui il comando seguente per avviare il progetto:
mvn spring-boot:run

Con questi comandi, il progetto Spring dovrebbe essere ora in esecuzione e pronto per l'uso. Assicurati che il tuo database PostgreSQL sia configurato correttamente e in esecuzione prima di avviare il progetto, dato che hai una dipendenza dal driver PostgreSQL.

***************************************
💡Configurazione
Assicurati di configurare il file application.properties nella directory src/main/resources del progetto per impostare le configurazioni del database, della sicurezza e delle altre proprietà necessarie per il progetto.

🖊️AUTORE
Marco Tumminia
 
