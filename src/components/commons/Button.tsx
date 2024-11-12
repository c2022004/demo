import React from "react";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  href?: string;
  primary?: boolean;
  outline?: boolean;
  text?: boolean;
  disable?: boolean;
  rounded?: boolean;
  textborder?: boolean;
  small?: boolean;
  large?: boolean;
  lefticon?: React.ReactNode;
  righticon?: React.ReactNode;
  children: React.ReactNode;
  className?: string;
  onClick?: () => void;
}

// Tạo kiểu cho AnchorButton
const AnchorButton = React.forwardRef<HTMLAnchorElement, ButtonProps>(
  (
    {
      className = "",
      href,
      primary = false,
      outline = false,
      text = false,
      disable = false,
      rounded = false,
      textborder = false,
      small = false,
      large = false,
      children,
      lefticon,
      righticon,
      onClick,
      ...passProps
    },
    ref
  ) => {
    // Tạo className dựa trên props
    const classes = [
      "inline-flex items-center justify-center text-base font-bold min-w-[30px] px-4 py-2 border border-transparent",
      !text && "bg-white",
      disable && "pointer-events-none opacity-50",
      rounded &&
        "rounded-full shadow-sm border-[rgba(22,24,35,0.12)] hover:border-[rgba(22,24,35,0.2)] hover:bg-white",
      primary && "bg-primary text-white hover:bg-primary/90",
      outline &&
        "text-primary border-current rounded-full hover:border-current hover:bg-primary/5",
      textborder && "border-[#1618231f]",
      text && "border-none hover:underline",
      small && "min-w-[88px] py-1 px-4",
      large && "py-3.5 px-4 min-w-[140px]",
      className,
    ]
      .filter(Boolean)
      .join(" ");

    return (
      <a
        {...passProps}
        href={href}
        className={classes}
        ref={ref}
        onClick={disable ? undefined : onClick}
      >
        {lefticon && <span className="w-6 text-center">{lefticon}</span>}
        <span className={`${lefticon || righticon ? "mx-2" : ""}`}>
          {children}
        </span>
        {righticon && <span className="w-6 text-center">{righticon}</span>}
      </a>
    );
  }
);

// Tạo kiểu cho ButtonElement
const ButtonElement = React.forwardRef<HTMLButtonElement, ButtonProps>(
  (
    {
      className = "",
      primary = false,
      outline = false,
      text = false,
      disable = false,
      rounded = false,
      textborder = false,
      small = false,
      large = false,
      children,
      lefticon,
      righticon,
      onClick,
      ...passProps
    },
    ref
  ) => {
    const classes = [
      "inline-flex items-center justify-center text-base font-bold min-w-[30px] px-4 py-2 border border-transparent",
      !text && "bg-white",
      disable && "pointer-events-none opacity-50",
      rounded &&
        "rounded-full shadow-sm border-[rgba(22,24,35,0.12)] hover:border-[rgba(22,24,35,0.2)] hover:bg-white",
      primary && "bg-primary text-white hover:bg-primary/90",
      outline &&
        "text-primary border-current rounded-full hover:border-current hover:bg-primary/5",
      textborder && "border-[#1618231f]",
      text && "border-none hover:underline",
      small && "min-w-[88px] py-1 px-4",
      large && "py-3.5 px-4 min-w-[140px]",
      className,
    ]
      .filter(Boolean)
      .join(" ");

    return (
      <button
        {...passProps}
        className={classes}
        ref={ref}
        disabled={disable}
        onClick={disable ? undefined : onClick}
      >
        {lefticon && <span className="w-6 text-center">{lefticon}</span>}
        <span className={`${lefticon || righticon ? "mx-2" : ""}`}>
          {children}
        </span>
        {righticon && <span className="w-6 text-center">{righticon}</span>}
      </button>
    );
  }
);

// Component Button chính chọn biến thể phù hợp
const Button = React.forwardRef<
  HTMLButtonElement | HTMLAnchorElement,
  ButtonProps
>((props, ref) => {
  const { href } = props;
  if (href) {
    return (
      <AnchorButton {...props} ref={ref as React.Ref<HTMLAnchorElement>} />
    );
  }
  return <ButtonElement {...props} ref={ref as React.Ref<HTMLButtonElement>} />;
});

Button.displayName = "Button";

export default Button;
