package com.example.tp_retrofit;

public class AddItemDialogFragment extends DialogFragment {

    private EditText inputName, inputDescription;
    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_item, container, false);

        inputName = view.findViewById(R.id.inputName);
        inputDescription = view.findViewById(R.id.inputDescription);
        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            String name = inputName.getText().toString();
            String description = inputDescription.getText().toString();

            // You can create an Item object here and send it to API to add
            Item item = new Item(name, description);
            ((MainActivity) getActivity()).addItem(item);

            dismiss(); // Dismiss the dialog after saving
        });

        return view;
    }
}

