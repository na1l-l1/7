package com.example.lab7;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView outputText;
    private ListView fileListView;
    private final String fileName = "test.txt";
    private final String newFileName = "newTest.txt"; // Новый файл, который будет создан.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.editFileData);
        outputText = findViewById(R.id.textFileData);
        fileListView = findViewById(R.id.listViewFiles);
        Button saveButton = findViewById(R.id.btnSaveFile);
        Button loadButton = findViewById(R.id.btnLoadFile);
        Button createFileButton = findViewById(R.id.btnCreateFile);  // Кнопка создания нового файла.

        saveButton.setOnClickListener(v -> writeFile());
        loadButton.setOnClickListener(v -> refreshFileList());
        createFileButton.setOnClickListener(v -> createNewFile());  // Действие для создания нового файла.

        refreshFileList();

        fileListView.setOnItemClickListener((parent, view, position, id) -> readAllFiles());
    }

    private void writeFile() {
        String content = inputText.getText().toString().trim();
        if (content.isEmpty()) {
            showToast("Введите текст перед сохранением");
            return;
        }

        File file = new File(getExternalFilesDir(null), fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            showToast("Файл сохранён: " + file.getAbsolutePath());
            refreshFileList();
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при сохранении файла");
        }
    }

    private void readAllFiles() {
        File file = new File(getExternalFilesDir(null), fileName);

        if (!file.exists()) {
            outputText.setText("Нет файлов.");
            return;
        }

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Файл: ").append(file.getName()).append("\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            contentBuilder.append("Ошибка чтения файла\n");
        }

        outputText.setText(contentBuilder.toString());
    }


    private void refreshFileList() {
        File directory = getExternalFilesDir(null);
        File file = new File(directory, fileName);
        List<String> fileNames = new ArrayList<>();

        if (file.exists()) {
            fileNames.add(file.getName());
            fileListView.setEnabled(true);
        } else {
            fileNames.add("Файлов нет");
            fileListView.setEnabled(false);
            outputText.setText("Нет файлов.");
        }

        fileListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileNames));
    }


    private void createNewFile() {
        // Удаляем старый файл
        File file = new File(getExternalFilesDir(null), fileName);
        if (file.exists()) {
            file.delete();
        }

        // Создаем новый файл
        File newFile = new File(getExternalFilesDir(null), newFileName);
        try {
            if (newFile.createNewFile()) {
                showToast("Новый файл создан: " + newFile.getAbsolutePath());
                refreshFileList();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при создании файла");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
