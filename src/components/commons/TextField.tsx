interface TextField {
  username?: string;
  currency?: string;
  type?: string;
  label?: string;
}

function TextField({ type, label }: TextField) {
  return (
    <div className="p-2">
      <label
        htmlFor="price"
        className="block text-sm/6 font-medium text-gray-900"
      >
        {label}
      </label>
      <div className="relative mt-2 rounded-md shadow-sm">
        <input
          id="price"
          name="price"
          type={type}
          placeholder="Username"
          className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm/6"
        />
      </div>
    </div>
  );
}

export default TextField;
