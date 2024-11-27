import { ChangeEvent } from "react";

import React from "react";
interface TextField {
  name?: string;
  id?:string
  currency?: string;
  type?: string;
  label?: string;
  tabIndex?: number;
  placeholder?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

function TextField({name,id, type, label,placeholder, onChange, tabIndex }: TextField) {
  return (
    <div className="p-2">
      <label htmlFor={id}
        className="block text-sm/6 font-medium text-gray-900"
      >
        {label}
      </label>
      <div className="relative mt-2 rounded-md shadow-sm">
        <input
        
          tabIndex={tabIndex}
          id={id}
          name={name}
          type={type}
          onChange={onChange}
          placeholder={placeholder}
          className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-yellow-200 sm:text-sm/6"
        />
      </div>
    </div>
  );
}

export default TextField;
