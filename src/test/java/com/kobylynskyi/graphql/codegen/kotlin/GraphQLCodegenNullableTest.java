package com.kobylynskyi.graphql.codegen.kotlin;

import com.kobylynskyi.graphql.codegen.TestUtils;
import com.kobylynskyi.graphql.codegen.model.GeneratedLanguage;
import com.kobylynskyi.graphql.codegen.model.MappingConfig;
import com.kobylynskyi.graphql.codegen.supplier.SchemaFinder;
import com.kobylynskyi.graphql.codegen.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

import static com.kobylynskyi.graphql.codegen.TestUtils.assertSameTrimmedContent;
import static com.kobylynskyi.graphql.codegen.TestUtils.getFileByName;

class GraphQLCodegenNullableTest {

    private final File outputBuildDir = new File("build/generated");
    private final File outputJavaClassesDir = new File("build/generated");
    private final MappingConfig mappingConfig = new MappingConfig();
    private final SchemaFinder schemaFinder = new SchemaFinder(Paths.get("src/test/resources"));

    @BeforeEach
    void init() {
        mappingConfig.setGeneratedLanguage(GeneratedLanguage.KOTLIN);
    }

    @AfterEach
    void cleanup() {
        Utils.deleteDir(outputBuildDir);
    }

    @Test
    void generatePrimitiveTypesResponseResolverClasses_nullable() throws Exception {
        mappingConfig.setGenerateApis(true);
        mappingConfig.setGenerateClient(true);
        schemaFinder.setIncludePattern("nullable-extend.graphqls");
        new KotlinGraphQLCodegen(schemaFinder.findSchemas(), outputBuildDir, mappingConfig, TestUtils.getStaticGeneratedInfo()).generate();

        File[] files = Objects.requireNonNull(outputJavaClassesDir.listFiles());

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Event.kt.txt"),
                getFileByName(files, "Event.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Null1QueryQueryResolver.kt.txt"),
                getFileByName(files, "Null1QueryQueryResolver.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Null2QueryQueryResolver.kt.txt"),
                getFileByName(files, "Null2QueryQueryResolver.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Null3QueryQueryResolver.kt.txt"),
                getFileByName(files, "Null3QueryQueryResolver.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Null4QueryQueryResolver.kt.txt"),
                getFileByName(files, "Null4QueryQueryResolver.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/Null5QueryQueryResolver.kt.txt"),
                getFileByName(files, "Null5QueryQueryResolver.kt"));

        assertSameTrimmedContent(
                new File("src/test/resources/expected-classes/kt/nullable/QueryResolver.kt.txt"),
                getFileByName(files, "QueryResolver.kt"));
    }
}