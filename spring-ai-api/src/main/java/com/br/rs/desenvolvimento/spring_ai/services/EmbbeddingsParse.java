package com.br.rs.desenvolvimento.spring_ai.services;

// @Component
// @Slf4j
// public class EmbbeddingsParse implements ApplicationRunner {

// private final VectorStore vectorStore;

// @Value("classpath:documents/biblioteca.md")
// Resource mdFile;

// public EmbbeddingsParse(VectorStore vectorStore) {
// this.vectorStore = vectorStore;
// }

// @Override
// public void run(ApplicationArguments args) throws Exception {
// List<Document> documents = new ArrayList<>();

// EmbbeddingsParse.log.info("Loading .md files as Documents");

// var reader = new TextReader(this.mdFile);
// reader.getCustomMetadata().put("titulo", "Biblioteca");
// reader.setCharset(Charset.defaultCharset());
// documents.addAll(reader.get());

// var textSplitter = new TokenTextSplitter();
// var transformedDocuments = textSplitter.split(documents);

// EmbbeddingsParse.log.info("Creating and storing Embeddings from Documents");

// this.vectorStore.add(transformedDocuments);
// }


// }

